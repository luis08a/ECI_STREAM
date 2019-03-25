package edu.eci.arsw.eci_stream.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class AppSecurityConfig	 extends WebSecurityConfigurerAdapter {
	

	@Autowired
	AuthProvider authProvider;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.authenticationProvider(authProvider);
	}
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/register","/").permitAll()
                //.anyRequest().authenticated()
                .and()
            .formLogin()
                .defaultSuccessUrl("/main.html").permitAll()
            .and()
            .csrf().disable();
    }
}


