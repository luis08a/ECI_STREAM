package edu.eci.arsw.eci_stream.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Room
 */
public class Room {
    private List<User> users;
    private User teacher;
    private Long id;
    private final RoomInfo information;

    public Room(Long id, User teacher, RoomInfo information) {
        this.setId(id);
        this.teacher = teacher;
        this.information = information;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the information
     */
    public RoomInfo getInformation() {
        return information;
    }

    public void addUser(User u) {
        if(users==null){
            users=new ArrayList<User>();
        }
        users.add(u);
    }

    public void removeUser(String userName) {
        for(User u: users){
            if(u.getName().equals(userName))
            users.remove(u);
            break;
        }
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @return the teacher
     */
    public User getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
}