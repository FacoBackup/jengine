package com.pine.service.loader.impl.response;

import com.pine.FSUtil;

import java.util.List;
import java.util.stream.Collectors;

public class AudioLoaderResponse extends AbstractLoaderResponse {
    private List<String> audio;

    public AudioLoaderResponse(){}

    public AudioLoaderResponse(boolean isLoaded, String filePath, List<String> audio) {
        super(isLoaded, filePath, audio.stream().map(a -> new ResourceInfo(FSUtil.getNameFromPath(filePath), a)).collect(Collectors.toList()));
        this.audio = audio;
    }

    public List<String> getAudio() {
        return audio;
    }

    public void setAudio(List<String> audio) {
        this.audio = audio;
    }
}
