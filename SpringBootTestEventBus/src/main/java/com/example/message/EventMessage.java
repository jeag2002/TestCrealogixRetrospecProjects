package com.example.message;

import com.example.entity.UserDetails;

public class EventMessage {
    
    private UserDetails data;
    
    public EventMessage(UserDetails message) {
        this.data = message;
    }

    public UserDetails getMessage() {
        return data;
    }

}
