package edu.eci.arsw.eci_stream.persistence.persistenceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.model.entities.Room;
import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.persistence.StreamPersistence;

/**
 * InMemoryPersistence
 */
@Service
public class MockPersistence implements StreamPersistence {
    private Map<String,User> mockUsers= new HashMap<String,User>();
    
	public MockPersistence() {
        
		User u1 = new User("user1","user1@mail.com","porcifrar1");
		User u2 = new User("user2","user2@mail.com","porcifrar2");
        mockUsers.put(u1.getName(), u1);
        mockUsers.put(u2.getName(), u2);
    }
    
    @Override
    public List<User> getUsers() {
        System.out.println("\n"+"Users--------------------------------------------------------DSFHGJGFdfgfhjkhghfdDS>DFG"+"\n");
        return new ArrayList<User>(mockUsers.values());
    }

    @Override
    public void registerUser(User u, String password) {
        mockUsers.put(u.getName(), u);
    }

    @Override
    public List<Room> getRooms() {
        return null;
    }

    @Override
    public User getUserByName(String userName) {
        return mockUsers.get(userName);
    }

    
}