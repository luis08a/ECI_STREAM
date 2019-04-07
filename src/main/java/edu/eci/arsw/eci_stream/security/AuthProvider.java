package edu.eci.arsw.eci_stream.security;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.persistence.UserPersistence;
import edu.eci.arsw.eci_stream.persistence.persistenceImpl.MockPersistence;
import edu.eci.arsw.eci_stream.persistence.persistenceImpl.dataBaseException;
import edu.eci.arsw.eci_stream.persistence.persistenceImpl.dataBasePersistance;

@Service
public class AuthProvider implements AuthenticationProvider {

	@Autowired
	MockPersistence mp;
	@Autowired
	dataBasePersistance dbP;
	@Autowired
	UserPersistence up;
	@Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
		if (up.findById(auth.getPrincipal().toString()).isPresent() ) {
			return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials());
		}
        throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
    }

}


