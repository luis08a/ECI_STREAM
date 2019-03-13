package edu.eci.arsw.eci_stream.persistence.persistenceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.model.entities.Room;
import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.persistence.StreamPersistence;

/**
 * InMemoryPersistence
 */
@Service
public class InMemoryPersistence implements StreamPersistence {
	
	
    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void registerUser(User u, String password) {

    }

    @Override
    public List<Room> getRooms() {
        return null;
    }

    
}