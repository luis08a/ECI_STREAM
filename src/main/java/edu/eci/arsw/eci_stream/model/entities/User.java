package edu.eci.arsw.eci_stream.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
    @Id
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password; 

    public User(String name, String email,String pass){
        this.name=name;
        this.email=email; 
        this.password=pass;
    }

    private User() {}

	/**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
	public String getPassword() {
		return password;
	}
    
	public void setPassword(String pass) {
		password = pass;
	}
    
	public Object getPasswordConfirm() {
		return password;
	}
    
}
