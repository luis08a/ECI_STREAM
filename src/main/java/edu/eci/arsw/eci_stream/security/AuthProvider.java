package edu.eci.arsw.eci_stream.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.persistence.persistenceImpl.MockPersistence;

@Service
public class AuthProvider implements AuthenticationProvider {

	@Autowired
	MockPersistence mp;

	@Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {         
    	if (mp.getUserByName(auth.getPrincipal().toString()) != null && mp.getUserByName(auth.getPrincipal().toString()).getPassword().equals(auth.getCredentials()))
    		return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials());
    	
        throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
    }

}