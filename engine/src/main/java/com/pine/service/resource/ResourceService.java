package com.pine.service.resource;

import com.pine.Loggable;
import com.pine.PBean;
import com.pine.PInject;
import com.pine.Updatable;
import com.pine.repository.ClockRepository;
import com.pine.service.loader.ResourceLoaderService;
import com.pine.service.loader.impl.response.AbstractLoaderResponse;
import com.pine.service.resource.resource.*;
import com.pine.tasks.SyncTask;

import java.util.*;
import java.util.stream.Collectors;

@PBean
public class ResourceService implements Loggable, SyncTask {
    public static final int MAX_TIMEOUT = 5 * 60 * 1000;

    @PInject
    public List<AbstractResourceService> implementations;

    @PInject
    public ClockRepository clock;

    @PInject
    public ResourceLoaderService loader;

    private final Map<String, IResource> resources = new HashMap<>();
    private final Map<String, Long> sinceLastUse = new HashMap<>();
    private final Map<ResourceType, List<String>> usedResources = new HashMap<>();
    private long sinceLastCleanup;

    public IResource addResource(ResourceCreationData data) {
        IResource instance = null;
        for (var i : implementations) {
            if (i.getResourceType() == data.getResourceType()) {
                instance = i.add(data);
            }
        }
        if (instance == null) {
            getLogger().warn("Resource could not be initialized correctly: {}", data.getResourceType());
            return null;
        }
        resources.put(instance.getId(), instance);
        sinceLastUse.put(instance.getId(), System.currentTimeMillis());
        return instance;
    }

    public void removeResource(String id) {
        IResource resource = resources.get(id);
        if (resource == null) {
            getLogger().warn("Resource not found: {}", id);
            return;
        }

        if (resource.isStatic()) {
            return;
        }

        for (var i : implementations) {
            if (i.getResourceType() == resource.getResourceType()) {
                i.remove(resource);
            }
        }
        resources.remove(id);
    }

    public <T extends IResource, R extends IResourceRuntimeData> void bind(T instance, R data) {
        for (var i : implementations) {
            if (i.getResourceType() == instance.getResourceType()) {
                i.bind(instance, data);
            }
        }
    }

    public <T extends IResource> void bind(T instance) {
        for (var i : implementations) {
            if (i.getResourceType() == instance.getResourceType()) {
                i.bind(instance);
            }
        }
    }

    public List<IResource> getAllByType(ResourceType type) {
        return resources.values().stream().filter(r -> r.getResourceType().equals(type)).collect(Collectors.toList());
    }

    public void shutdown() {
        implementations.forEach(i -> i.shutdown(getAllByType(i.getResourceType())));
    }

    @Override
    public void sync() {
        if ((clock.totalTime - sinceLastCleanup) >= MAX_TIMEOUT) {
            sinceLastCleanup = clock.totalTime;
            int removed = 0;
            for (var entry : sinceLastUse.entrySet()) {
                if (System.currentTimeMillis() - entry.getValue() > MAX_TIMEOUT) {
                    removeResource(entry.getKey());
                    removed++;
                }
            }
            getLogger().warn("Removed {} unused resources", removed);
            usedResources.clear();
            resources.values().forEach(resource -> {
                usedResources.putIfAbsent(resource.getResourceType(), new ArrayList<>());
                usedResources.get(resource.getResourceType()).add(resource.getId());
            });
        }
    }

    public IResource getById(String id) {
        return resources.get(id);
    }

    public void makeStatic(IResource resource) {
        resource.makeStatic();
    }

    public IResource getOrCreateResource(String id) {
        IResource found = getById(id);
        if(found != null) {
            return found;
        }

        for(var history : loader.repository.loadedResources){
            for(var record : history.getRecords()){
                if(Objects.equals(record.id, id)) {
                    AbstractLoaderResponse load = loader.load(history.getFilePath(), false, null);
                    if (load != null) {
                        return getById(id);
                    } else {
                        getLogger().error("Could not load resource: {}", history.getFilePath());
                    }
                }
            }
        }

        return null;
    }
}
