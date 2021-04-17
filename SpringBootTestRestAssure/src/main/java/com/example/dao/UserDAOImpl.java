package com.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.UserDetails;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAOImpl.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    /** The entity manager. */
    @Autowired
    private EntityManager entityManager;

    /* (non-Javadoc)
     * @see com.example.dao.UserDAO#findAll()
     */
    @Override
    public List<UserDetails> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<UserDetails> theQuery = currentSession.createQuery("from UserDetails", UserDetails.class);

        List<UserDetails> users = theQuery.getResultList();

        return users;

    }

    /* (non-Javadoc)
     * @see com.example.dao.UserDAO#findById(int)
     */
    @Override
    public UserDetails findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        UserDetails user = currentSession.get(UserDetails.class, id);

        return user;
    }

    /* (non-Javadoc)
     * @see com.example.dao.UserDAO#save(com.example.entity.User)
     */
    @Override
    public void save(UserDetails user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);

    }

    /* (non-Javadoc)
     * @see com.example.dao.UserDAO#deleteById(int)
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<UserDetails> theQuery = currentSession.createQuery("delete from UserDetails where id=:id");

        theQuery.setParameter("id", id);
        theQuery.executeUpdate();

    }

}
