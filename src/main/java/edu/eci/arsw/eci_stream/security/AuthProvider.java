package edu.eci.arsw.eci_stream.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.persistence.persistenceImpl.MockPersistence;
import edu.eci.arsw.eci_stream.persistence.persistenceImpl.dataBaseException;
import edu.eci.arsw.eci_stream.persistence.persistenceImpl.dataBasePersistance;

@Service
public class AuthProvider implements AuthenticationProvider {

	@Autowired
	MockPersistence mp;
	@Autowired
	dataBasePersistance dbP;
	@Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {         
    	try {
			if (mp.getUserByName(auth.getPrincipal().toString()) != null /*&& dbP.consultarUsuarios(auth.getName().toString(), auth.getCredentials().toString())*/) {
				return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
    }

}


