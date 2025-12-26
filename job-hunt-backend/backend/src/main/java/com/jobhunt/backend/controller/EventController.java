package com.jobhunt.backend.controller;

import com.jobhunt.backend.dto.EventRequest;
import com.jobhunt.backend.model.Event;
import com.jobhunt.backend.service.EventService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.jobhunt.backend.dto.PlatformTimeResponse;
import com.jobhunt.backend.dto.WeeklyAnalyticsResponse;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event createEvent(@RequestBody EventRequest request) {
        return eventService.saveEvent(request);
    }
    
    @GetMapping("/analytics/total-time")
    public Long getTotalTime(@RequestParam Long userId) {
        return eventService.getTotalTimeSpent(userId);
    }
    
    @GetMapping("/analytics/platform-time")
    public List<PlatformTimeResponse> getPlatformWiseTime(@RequestParam Long userId) {
        return eventService.getPlatformWiseTime(userId);
    }

    @GetMapping("/analytics/weekly")
    public List<WeeklyAnalyticsResponse> getWeeklyAnalytics(@RequestParam Long userId) {
        return eventService.getWeeklyAnalytics(userId);
    }
}
