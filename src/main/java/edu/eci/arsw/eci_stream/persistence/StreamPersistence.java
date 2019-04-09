package edu.eci.arsw.eci_stream.persistence;

import java.util.List;

import edu.eci.arsw.eci_stream.model.entities.Room;
import edu.eci.arsw.eci_stream.model.entities.RoomInfo;
import edu.eci.arsw.eci_stream.model.entities.User;

/**
 * StreamPersistence
 */
public interface StreamPersistence {

    public List<User> getUsers() throws PersistenceException;

    public User getUserByName(String userName) throws PersistenceException;

    public void registerUser(User u, String password) throws PersistenceException;

    public void joinInARoom(User u, long roomId) throws PersistenceException;

    public void leaveRoom(String userId, Long roomId) throws PersistenceException;

    public List<Room> getRooms() throws PersistenceException;

    public Room getRoomById(Long id) throws PersistenceException;

    public void createARoom(Room room) throws PersistenceException;
}