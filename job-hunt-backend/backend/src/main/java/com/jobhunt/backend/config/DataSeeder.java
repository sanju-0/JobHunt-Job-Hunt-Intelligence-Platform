package com.jobhunt.backend.config;

import com.jobhunt.backend.model.Event;
import com.jobhunt.backend.model.User;
import com.jobhunt.backend.repository.EventRepository;
import com.jobhunt.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(
            UserRepository userRepository,
            EventRepository eventRepository) {

        return args -> {

            // Avoid duplicate seeding
            if (userRepository.count() > 0) {
                return;
            }

            // Create User
            User user = new User();
            user.setEmail("demo@jobhunt.com");
            user.setPassword("demo123");
            userRepository.save(user);

            // Event 1
            Event e1 = new Event();
            e1.setPlatform("LINKEDIN");
            e1.setEventType("VISIT");
            e1.setDuration(120L);
            e1.setUser(user);
            e1.setTimestamp(LocalDateTime.now().minusDays(3));

            // Event 2
            Event e2 = new Event();
            e2.setPlatform("LINKEDIN");
            e2.setEventType("APPLY");
            e2.setDuration(300L);
            e2.setUser(user);
            e2.setTimestamp(LocalDateTime.now().minusDays(2));

            // Event 3
            Event e3 = new Event();
            e3.setPlatform("INDEED");
            e3.setEventType("VISIT");
            e3.setDuration(180L);
            e3.setUser(user);
            e3.setTimestamp(LocalDateTime.now().minusDays(1));

            eventRepository.save(e1);
            eventRepository.save(e2);
            eventRepository.save(e3);
        };
    }
}
