package edu.eci.arsw.eci_stream.persistence;

import java.util.List;

import edu.eci.arsw.eci_stream.model.entities.Room;
import edu.eci.arsw.eci_stream.model.entities.User;

/**
 * StreamPersistence
 */
public interface StreamPersistence {

    public List<User> getUsers();

    public void registerUser(User u, String password);

    public List<Room> getRooms();

    //public void createARoom(User u, String title);
}