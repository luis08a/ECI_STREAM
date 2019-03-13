package edu.eci.arsw.eci_stream.model.entities;

import java.util.List;

/**
 * Room
 */
public class Room {
    private List<User> users;
    private User teacher;

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