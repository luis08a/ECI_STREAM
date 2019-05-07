package edu.eci.arsw.eci_stream.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuarios")
public class User {
    @NotNull
    private String username;
    @NotNull
    @Id
    @Email
    private String email;
    @NotNull
    private String password; 

    public User(String name, String email,String pass){
        this.username=name;
        this.email=email; 
        this.password=pass;
    }

    private User() {}

	/**
     * @return the name
     */
    public String getName() {
        return username;
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
        this.username = name;
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
