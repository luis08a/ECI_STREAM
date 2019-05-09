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
public class InMemoryStreamPersistence implements StreamPersistence {

    private Map<Long, Room> mockRooms;

    public InMemoryStreamPersistence() {
        mockRooms = new HashMap<Long, Room>();
    }

    @Override
    public List<Room> getRooms() throws PersistenceException {
        if (!mockRooms.isEmpty()) {
            return new ArrayList<Room>(mockRooms.values());
        } else {
            throw new PersistenceException("There are not rooms yet");
        }
    }

    @Override
    public Room getRoomById(Long id) {
        return mockRooms.get(id);
    }

    @Override
    public Room createARoom(Room room) {
        for (long i = 0;; i++) {
            if (!mockRooms.keySet().contains(i)) {
                room.setId(i);
                mockRooms.put(i, room);
                return room;
                //break;
            }
        }
    }

    @Override
    public void joinInARoom(User u, long roomId) throws PersistenceException {
        if (mockRooms.containsKey(roomId)) {
            mockRooms.get(roomId).addUser(u);
        } else {
            throw new PersistenceException("The room doesn´t exist");
        }
    }

    @Override
    public void leaveRoom(String userId, Long roomId) throws PersistenceException {
        if (mockRooms.containsKey(roomId)) {
            mockRooms.get(roomId).removeUser(userId);
        } else {
            throw new PersistenceException("The room doesn´t exist");
        }
    }

    @Override
    public void eraseRoom(Long roomId) throws PersistenceException {
        mockRooms.remove(roomId);
    }
}