package edu.eci.arsw.eci_stream.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import edu.eci.arsw.eci_stream.persistence.UserPersistence;

@Service
public class AuthProvider implements AuthenticationProvider {

	@Autowired
	UserPersistence up;
	@Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String name = auth.getPrincipal().toString();
        System.out.println("");System.out.println("");System.out.println("");System.out.println("");System.out.println("");System.out.println("");
        System.out.println(name);
        System.out.println("");System.out.println("");System.out.println("");System.out.println("");System.out.println("");System.out.println("");
        String pass = auth.getCredentials().toString();
        System.out.println("inicio el auth");
        System.out.println("existe"+up.findUser(name, pass));
        //up.findById(auth.getPrincipal().toString()).isPresent()
		if (!(up.findUser(name, pass).isEmpty())) {
            
            System.out.println("entro al auth");
			return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(),new ArrayList<>());
		}
        throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
    }

}