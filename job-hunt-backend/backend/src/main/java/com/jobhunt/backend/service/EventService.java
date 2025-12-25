package com.jobhunt.backend.service;

import com.jobhunt.backend.dto.EventRequest;
import com.jobhunt.backend.model.Event;
import com.jobhunt.backend.model.User;
import com.jobhunt.backend.repository.EventRepository;
import com.jobhunt.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.jobhunt.backend.dto.PlatformTimeResponse;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Event saveEvent(EventRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = new Event();
        event.setPlatform(request.getPlatform());
        event.setEventType(request.getEventType());
        event.setDuration(request.getDuration());
        event.setUser(user);

        return eventRepository.save(event);
    }

    public Long getTotalTimeSpent(Long userId) {
        Long totalTime = eventRepository.getTotalTimeSpentByUser(userId);
        return totalTime != null ? totalTime : 0L;
    }


    public List<PlatformTimeResponse> getPlatformWiseTime(Long userId) {
        return eventRepository.getPlatformWiseTime(userId);
    }


}
