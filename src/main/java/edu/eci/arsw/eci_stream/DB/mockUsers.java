package edu.eci.arsw.eci_stream.DB;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.eci.arsw.eci_stream.model.entities.User;


@Component
public class mockUsers {
	
	public static List<User> mockUsers = new ArrayList<User>();
	
	public mockUsers() {
		User u1 = new User("user1","user1@mail.com","porcifrar1");
		User u2 = new User("user2","user2@mail.com","porcifrar2");
		mockUsers.add(u2);
		mockUsers.add(u1);
	}
	
	
	
	public static User findByUsername(String name) {
		User y=null;
		for(User u:mockUsers) {
			if(u.getName().equals(name)) {
				y=u;
			}
		}
		return y;
	}

	
	public void save(User u) {
		mockUsers.add(u);
	}
}
