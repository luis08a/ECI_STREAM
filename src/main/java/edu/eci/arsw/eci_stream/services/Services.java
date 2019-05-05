package edu.eci.arsw.eci_stream.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.model.entities.Room;
import edu.eci.arsw.eci_stream.model.entities.RoomInfo;
import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.persistence.PersistenceException;
import edu.eci.arsw.eci_stream.persistence.StreamPersistence;
import edu.eci.arsw.eci_stream.persistence.UserPersistence;
import edu.eci.arsw.eci_stream.persistence.persistenceImpl.dataBasePersistance;

/**
 * services
 */
@Service
public class Services  {
    @Autowired
    @Qualifier("mock persistence")
    StreamPersistence sp;
    @Autowired
    UserPersistence up;
    @Autowired
    dataBasePersistance dbp;
    //User Methods
    public Iterable<User> getAllUsers() throws PersistenceException {
        return up.findAll();
    }
    public User findById(String email) throws PersistenceException{
        Optional<User> user= up.findById(email);
        if(user.isPresent())return user.get();
        throw new PersistenceException("User does not exist");
    }
    public List<User> consultUserByName(String userName) throws PersistenceException {
        return up.findByusername(userName);
    }

    public void createUser(User u) throws PersistenceException {
        up.save(u);
    }

    public void updateUser(User u) throws PersistenceException{
        up.save(u);
    }
    public List<User> getUsersByRoom(Long roomId) throws PersistenceException {
        return sp.getRoomById(roomId).getUsers();
    }
    public void joinInAroom(User u, Long roomId) throws PersistenceException {
        sp.joinInARoom(u,roomId);
    }
    public void leaveRoom(Long roomId, String UserEmail){
        
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

    public Boolean rating(String name, int rating){
        boolean hecho =false;
        try{
        User u = up.findByusername(name).get(0);
        float r = (u.getRating()+rating)/2;
        hecho=dbp.updateRating(r, name);
        return (hecho);
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

}