package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.dao.logs.LogDetailsRepository;
import com.example.listener.EventListener;
import com.google.common.eventbus.EventBus;

@Configuration
public class EventBusConfig {
    
    @Autowired
    private LogDetailsRepository repository;
    
    @Bean
    public EventBus eventBus() {
        EventBus eventbus = new EventBus();
        eventbus.register(new EventListener(repository));
        return eventbus;
    }

}
