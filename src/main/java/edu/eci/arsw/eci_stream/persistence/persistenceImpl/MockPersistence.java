package edu.eci.arsw.eci_stream.persistence.persistenceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.model.entities.Room;
import edu.eci.arsw.eci_stream.model.entities.RoomInfo;
import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.persistence.PersistenceException;
import edu.eci.arsw.eci_stream.persistence.StreamPersistence;

/**
 * InMemoryPersistence
 */
@Service
public class MockPersistence implements StreamPersistence {
    private Map<String, User> mockUsers = new HashMap<String, User>();
    private Map<Long,Room> mockRooms = new HashMap<Long,Room>();

    public MockPersistence() {

        User u1 = new User("user1", "user1@mail.com", "porcifrar1");
        User u2 = new User("user2", "user2@mail.com", "porcifrar2");
        mockUsers.put(u1.getName(), u1);
        mockUsers.put(u2.getName(), u2);

        Room r1 = new Room((long) 1, u1, new RoomInfo("","",null,null));
        mockRooms.put((long) 1, r1);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<User>(mockUsers.values());
    }

    @Override
    public void registerUser(User u, String password) {
        mockUsers.put(u.getName(), u);
    }

    @Override
    public List<Room> getRooms() throws PersistenceException {
        if(!mockRooms.isEmpty()){
            return new ArrayList<Room>(mockRooms.values());
        }
        else{
            throw new PersistenceException("There are not rooms yet");
        }
    }

    @Override
    public User getUserByName(String userName) {
        return mockUsers.get(userName);
    }

    @Override
    public Room getRoomById(Long id) {
        return mockRooms.get(id);
    }

    @Override
    public void createARoom(User user, RoomInfo information) {
        for(long i=0;;i++){
            if(!mockRooms.keySet().contains(i)){
                mockRooms.put(i, new Room(i, user, information));
                break;
            }
        }
    }

    
}