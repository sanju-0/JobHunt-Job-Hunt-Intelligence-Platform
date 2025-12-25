package com.jobhunt.backend.dto;

public class PlatformTimeResponse {

    private String platform;
    private Long totalTime;

    public PlatformTimeResponse(String platform, Long totalTime) {
        this.platform = platform;
        this.totalTime = totalTime;
    }

    public String getPlatform() {
        return platform;
    }

    public Long getTotalTime() {
        return totalTime;
    }
}