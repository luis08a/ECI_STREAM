package edu.eci.arsw.eci_stream.persistence.persistenceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
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
@Qualifier("mock persistence")
public class MockPersistence implements StreamPersistence {

   

    @Override
    public void joinInARoom(User u, long roomId) throws PersistenceException {

    }

    @Override
    public void leaveRoom(String userId, Long roomId) throws PersistenceException {

    }

    @Override
    public List<Room> getRooms() throws PersistenceException {
        return null;
    }

    @Override
    public Room getRoomById(Long id) throws PersistenceException {
        return null;
    }

    @Override
    public void createARoom(Room room) throws PersistenceException {

    }
   /* private Map<String, User> mockUsers = new HashMap<String, User>();
    private Map<Long, Room> mockRooms = new HashMap<Long, Room>();

    public MockPersistence() {

        User t1 = new User("teacher1", "teacher1@mail.com", "porcifrar1",null,null);
        User u1 = new User("user1", "user1@mail.com", "porcifrar1",null,null);
        User u2 = new User("user2", "user2@mail.com", "porcifrar2",null,null);        
        User u3 = new User("user2", "user2@mail.com", "porcifrar2",null,null);
        mockUsers.put(u1.getName(), u1);
        mockUsers.put(u2.getName(), u2);
        mockUsers.put(u2.getName(), u3);

        Room r1 = new Room((long) 1, t1, new RoomInfo("programing","Room1",null,"It´s a mock room"));
        r1.addUser(u1);
        r1.addUser(u2);
        r1.addUser(u2);

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
    public void createARoom(Room room) {
        for(long i=0;;i++){
            if(!mockRooms.keySet().contains(i)){
                room.setId(i);
                mockRooms.put(i, room);
                break;
            }
        }
    }

    @Override
    public void joinInARoom(User u, long roomId) throws PersistenceException {
        if(mockRooms.containsKey(roomId)){
            mockRooms.get(roomId).addUser(u);
        }
        else{
            throw new PersistenceException("The room doesn´t exist");
        }
    }

    @Override
    public void leaveRoom(String userId, Long roomId) throws PersistenceException {
        if(mockRooms.containsKey(roomId)){
            mockRooms.get(roomId).removeUser(userId);
        }
        else{
            throw new PersistenceException("The room doesn´t exist");
        }
    }*/

    @Override
    public void eraseRoom(Long roomId) throws PersistenceException {

    }
}