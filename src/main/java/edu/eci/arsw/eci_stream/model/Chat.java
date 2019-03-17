package edu.eci.arsw.eci_stream.model;

import edu.eci.arsw.eci_stream.model.entities.User;

/**
 * Chat
 */
public interface Chat {

    public void sucribe(User u);

    public void comment(String message);
}