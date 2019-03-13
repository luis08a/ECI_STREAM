package edu.eci.arsw.eci_stream.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.DB.mockUsers;

@Service
public class AuthProvider implements AuthenticationProvider {

	@Autowired
	mockUsers mu;

	@Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

 	@SuppressWarnings("serial")
    private static Map<String, String> SIMPLE_USERS = new HashMap<String, String>(2) {{
        put("joe", "joe");
        put("bob", "bob");
    }};
   
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {         
    	if (mu.findByUsername(auth.getPrincipal().toString()) != null && mu.findByUsername(auth.getPrincipal().toString()).getPassword().equals(auth.getCredentials()))
    		return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials());
    	
        throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
    }

}