package com.pine.app.core.ui.view.tree;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Branch {
    private final List<Branch> branches = new ArrayList<>();
    private String label;
    private String name;
    private final String id;
    private final String key;

    public Branch(String name, String id) {
        this.name = name;
        this.id = id;
        this.key = "##" + DigestUtils.sha1Hex(UUID.randomUUID().toString());
        this.label = name + key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.label = name + key;
    }

    public String getId() {
        return id;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void addBranch(Branch child) {
        branches.add(child);
    }

    public String getLabel() {
        return label;
    }
}
