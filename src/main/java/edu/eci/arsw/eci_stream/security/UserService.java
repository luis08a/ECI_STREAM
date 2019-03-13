package edu.eci.arsw.eci_stream.security;

import edu.eci.arsw.eci_stream.model.entities.User;

public interface UserService {
	void save(User user);

    User findByUsername(String username);

}
