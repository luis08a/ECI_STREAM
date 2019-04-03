package edu.eci.arsw.eci_stream.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
    
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/resources/**")
            .antMatchers("/**.css")
            .antMatchers("/resources/static/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()                
                .antMatchers("/", "/register", "/register.html", "/styles/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .defaultSuccessUrl("/main.html").permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/")
            .and()
                .csrf().disable();
    }
}