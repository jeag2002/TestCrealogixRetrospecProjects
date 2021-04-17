package com.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dao.logs.LogDetailsRepository;
import com.example.entity.LogDetails;
import com.example.message.EventMessage;
import com.google.common.eventbus.Subscribe;

public class EventListener {
    
    private LogDetailsRepository repository;
    
    /** The logger. */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    public EventListener(LogDetailsRepository repo){
        this.repository = repo;
    }
    
    
    @Subscribe
    public void onEvent(EventMessage message) {
        logger.info("[EVENTLISTENER] Event: {}", message.getMessage());
        repository.save(new LogDetails(message.getMessage().toString()));
    }

}
