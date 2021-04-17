package com.example;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.dao.UserDAO;
import com.example.entity.UserDetails;
import com.example.message.EventMessage;
import com.google.common.eventbus.EventBus;


// TODO: Auto-generated Javadoc
/**
 * The Class MyRunner.
 */
@Component
public class MyRunner implements CommandLineRunner {
    /** The dao. */
    @Autowired
    private UserDAO dao;
    
    @Autowired
    private EventBus eventbus;
    
    /**
     * run.
     */
    @Override
    public void run(String... args) throws Exception {
        UserDetails user = new UserDetails();
        user.setEmail("pepe@gmail.com");
        user.setPassword("1234");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        dao.save(user);
        
        EventMessage message = new EventMessage(user);
        eventbus.post(message);
    }
}
