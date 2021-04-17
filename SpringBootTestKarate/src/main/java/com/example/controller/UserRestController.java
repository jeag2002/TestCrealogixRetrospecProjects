/*
 * Test
 */
package com.example.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.UserDetails;
import com.example.service.UserService;


/**
 * The Class UserRestController.
 */
@RestController
public class UserRestController {

    /** The user service. */
    @Autowired
    private UserService userService;

    /**
     * Find all.
     * @return the list
     */
    @GetMapping("/api/users")
    public List<UserDetails> findAll() {
        return userService.findAll();
    }

    /**
     * Gets the user.
     * @param userId
     *            the user id
     * @return the user
     */

    @GetMapping("/api/users/{userId}")
    public UserDetails getUser(@PathVariable int userId) {
        UserDetails user = userService.findById(userId);

        if (user == null) {
            throw new RuntimeException("User id not found -" + userId);
        }
        return user;
    }

    /**
     * Adds the user.
     * @param user
     *            the user
     * @return the user
     */

    @PostMapping("/api/users")
    public UserDetails addUser(@RequestBody UserDetails user) {
        user.setId(0);

        userService.save(user);

        return user;

    }

    /**
     * Update user.
     *
     * @param user
     *            the user
     * @return the user
     */
    @PutMapping("/api/users")
    public UserDetails updateUser(@RequestBody UserDetails user) {

        userService.save(user);

        return user;
    }

    /**
     * Detete user.
     *
     * @param userId
     *            the user id
     * @return the string
     */
    @DeleteMapping("/api/users/{userId}")
    public String deteteUser(@PathVariable int userId) {

        UserDetails user = userService.findById(userId);

        if (user == null) {
            throw new RuntimeException("User id not found -" + userId);
        }

        userService.deleteById(userId);

        // Esto método, recibira el id de un usuario por URL y se borrará de la bd.
        return "Deleted user id - " + userId;
    }

}
