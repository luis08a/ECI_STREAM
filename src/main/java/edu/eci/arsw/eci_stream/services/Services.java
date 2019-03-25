package edu.eci.arsw.eci_stream.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.model.entities.Room;
import edu.eci.arsw.eci_stream.model.entities.RoomInfo;
import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.persistence.PersistenceException;
import edu.eci.arsw.eci_stream.persistence.StreamPersistence;

/**
 * services
 */
@Service
public class Services  {
    @Autowired
    StreamPersistence sp;

    //User Methods
    public List<User> getAllUsers() throws PersistenceException {
        return sp.getUsers();
    }

    public User consultUserByName(String userName) throws PersistenceException {
        return sp.getUserByName(userName);
    }

    public void createUser(User u, String password) throws PersistenceException {
        sp.registerUser(u, password);
    }
    public List<User> getUsersByRoom(Long roomId) throws PersistenceException {
            return sp.getRoomById(roomId).getUsers();
    }
    public void joinInAroom(User u, Long roomId) throws PersistenceException {
        sp.joinInARoom(u,roomId);
    }

    //Room methods
    public List<Room> getAllRooms() throws PersistenceException {
        return sp.getRooms();
    }

    public Room getRoomById(Long id) throws PersistenceException {
        return sp.getRoomById(id);
    }

    public void createRoom(Room room) throws PersistenceException {
        sp.createARoom(room);
    }

}