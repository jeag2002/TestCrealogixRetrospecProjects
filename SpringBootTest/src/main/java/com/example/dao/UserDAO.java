package com.example.dao;


import java.util.List;

import com.example.entity.UserDetails;



// TODO: Auto-generated Javadoc
/**
 * The Interface UserDAO.
 */
public interface UserDAO {

    /**
     * Find all.
     *
     * @return the list
     */
    List<UserDetails> findAll();

    /**
     * Find by id.
     *
     * @param id the id
     * @return the user
     */
    UserDetails findById(int id);

    /**
     * Save.
     *
     * @param user the user
     */
    void save(UserDetails user);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(int id);
}
