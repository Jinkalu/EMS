package com.ems.employee_service.service.topic;

import com.ems.common.vo.EmployeeCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeEventPublisher {

    private final KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate;
    private static final String TOPIC = "employee-events";

    public EmployeeEventPublisher(KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEmployeeCreatedEvent(Long userId, String employeeId) {
        EmployeeCreatedEvent event = new EmployeeCreatedEvent(userId, employeeId);
        kafkaTemplate.send(TOPIC, event);
    }
}
