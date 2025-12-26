package com.jobhunt.backend.dto;

public class WeeklyAnalyticsResponse {

    private Integer week;
    private Long totalTime;
    private Long totalEvents;

    public WeeklyAnalyticsResponse(Integer week, Long totalTime, Long totalEvents) {
        this.week = week;
        this.totalTime = totalTime;
        this.totalEvents = totalEvents;
    }

    public Integer getWeek() {
        return week;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public Long getTotalEvents() {
        return totalEvents;
    }
}
