package edu.eci.arsw.eci_stream.persistence;

import java.util.List;

import edu.eci.arsw.eci_stream.model.entities.Room;
import edu.eci.arsw.eci_stream.model.entities.User;

/**
 * StreamPersistence
 */
public interface StreamPersistence {

    public List<User> getUsers() throws PersistenceException;

    public User getUserByName(String userName) throws PersistenceException;

    public void registerUser(User u, String password) throws PersistenceException;

    public List<Room> getRooms() throws PersistenceException;

    //public void createARoom(User u, String title);
}