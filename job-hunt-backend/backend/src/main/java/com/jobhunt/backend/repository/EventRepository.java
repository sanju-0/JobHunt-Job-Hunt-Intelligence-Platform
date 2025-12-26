package com.jobhunt.backend.repository;

import com.jobhunt.backend.model.Event;
import com.jobhunt.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.jobhunt.backend.dto.PlatformTimeResponse;
import com.jobhunt.backend.dto.WeeklyAnalyticsResponse;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByUser(User user);

    @Query("""
    SELECT SUM(e.duration)
    FROM Event e
    WHERE e.user.id = :userId
    """)
    Long getTotalTimeSpentByUser(Long userId);
    
    @Query("""
    SELECT new com.jobhunt.backend.dto.PlatformTimeResponse(
        e.platform,
        SUM(e.duration)
    )
    FROM Event e
    WHERE e.user.id = :userId
    GROUP BY e.platform
    """)
    List<PlatformTimeResponse> getPlatformWiseTime(Long userId);

    @Query("""
    SELECT
        FUNCTION('week', e.timestamp),
        SUM(e.duration),
        COUNT(e.id)
    FROM Event e
    WHERE e.user.id = :userId
    GROUP BY FUNCTION('week', e.timestamp)
    ORDER BY FUNCTION('week', e.timestamp)
    """)
    List<Object[]> getWeeklyAnalyticsRaw(Long userId);

}
