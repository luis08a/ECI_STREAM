package edu.eci.arsw.eci_stream.persistence.persistenceImpl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.model.entities.Room;
import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.persistence.PersistenceException;
import edu.eci.arsw.eci_stream.persistence.StreamPersistence;

@Service
public class streamPersistenceImpl implements StreamPersistence {
    List<Room> liveRooms;
    public streamPersistenceImpl(){
        liveRooms=new ArrayList<Room>();
    }  
 

    @Override
    public void joinInARoom(User u, long roomId) throws PersistenceException {
        getRoomById(roomId).addUser(u);


    }

    @Override
    public void leaveRoom(String userId, Long roomId) throws PersistenceException {
        getRoomById(roomId).removeUser(userId);

    }

    @Override
    public List<Room> getRooms() throws PersistenceException {
        return liveRooms;
    }

    @Override
    public Room getRoomById(Long id) throws PersistenceException {
        for(Room r:liveRooms){
            if(r.getId().equals(id)){
                return r;
            }
        }
        return null;

    }

    @Override
    public void createARoom(Room room) throws PersistenceException {
        liveRooms.add(room);

    }

    @Override
    public void eraseRoom(Long roomId) throws PersistenceException {
        liveRooms.remove(getRoomById(roomId));

    }

    

}