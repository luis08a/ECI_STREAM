package edu.eci.arsw.eci_stream.security.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.DB.mockUsers;
import edu.eci.arsw.eci_stream.model.entities.User;

import java.util.HashSet;
import java.util.Set;

public class UserDetailServiceImpl implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = mockUsers.findByUsername(username);
	        if (user == null) throw new UsernameNotFoundException(username);

	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        //for (Role role : user.getRoles()){
	        grantedAuthorities.add(new SimpleGrantedAuthority("USER_ROLE"));
	        

	        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);
	   
	}

}
