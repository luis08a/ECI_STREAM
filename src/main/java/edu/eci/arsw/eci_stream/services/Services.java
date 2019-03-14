package edu.eci.arsw.eci_stream.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.persistence.PersistenceException;
import edu.eci.arsw.eci_stream.persistence.StreamPersistence;
import edu.eci.arsw.eci_stream.persistence.persistenceImpl.MockPersistence;

/**
 * services
 */
public class Services  {
    //@Autowired
    StreamPersistence sp = new MockPersistence();

    public List<User> getAllUsers() throws PersistenceException {
        return sp.getUsers();
    }

    public User consultUserByName(String userName) throws PersistenceException {
        return sp.getUserByName(userName);
    }

    public void createUser(User u, String password) throws PersistenceException {
        sp.registerUser(u, password);
    }
}