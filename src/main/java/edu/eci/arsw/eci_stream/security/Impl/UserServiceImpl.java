package edu.eci.arsw.eci_stream.security.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.DB.mockUsers;
import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.security.UserService;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
	mockUsers mock;
    public void save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        
        mock.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return mock.findByUsername(username);
    }
}