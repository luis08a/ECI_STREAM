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
	
	private List<User> mockUsers = new ArrayList<User>();
	public void InMemoryPersistnace() {
		User u1 = new User("user1","user1@mail.com","porcifrar1");
		User u2 = new User("user2","user2@mail.com","porcifrar2");
		mockUsers.add(u2);
		mockUsers.add(u1);
		
	}
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