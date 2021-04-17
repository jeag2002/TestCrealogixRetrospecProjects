package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDAO;
import com.example.entity.UserDetails;
import com.example.oap.TrackTime;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

    /** The user DAO. */
    @Autowired
    private UserDAO userDAO;

    /* (non-Javadoc)
     * @see com.example.service.UserService#findAll()
     */
    //@TrackTime
    @Override
    public List<UserDetails> findAll() {
        List<UserDetails> listUsers = userDAO.findAll();
        return listUsers;
    }

    /* (non-Javadoc)
     * @see com.example.service.UserService#findById(int)
     */
    @TrackTime
    @Override
    public UserDetails findById(int id) {
        UserDetails user = userDAO.findById(id);
        return user;
    }

    /* (non-Javadoc)
     * @see com.example.service.UserService#save(com.example.entity.User)
     */
    @Override
    public void save(UserDetails user) {
        userDAO.save(user);

    }

    /* (non-Javadoc)
     * @see com.example.service.UserService#deleteById(int)
     */
    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

}

