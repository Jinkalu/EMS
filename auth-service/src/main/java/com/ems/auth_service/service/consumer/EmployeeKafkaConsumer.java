package com.ems.auth_service.service.consumer;

import com.ems.common.entity.User;
import com.ems.common.repository.UserRepository;
import com.ems.common.vo.EmployeeCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeKafkaConsumer {

    private final UserRepository userRepository;

    @KafkaListener(topics = "employee-events", groupId = "ems-group")
    public void consume(EmployeeCreatedEvent event) {
        log.info("event data {}",event);
        User user = userRepository.findById(event.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("user", "User not found"));
        user.setEmployeeId(event.getEmployeeId());
        userRepository.save(user);
    }
}

