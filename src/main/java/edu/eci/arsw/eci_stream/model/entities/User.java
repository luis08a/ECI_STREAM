package edu.eci.arsw.eci_stream.model.entities;

import java.beans.Transient;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.access.prepost.PostFilter;

@Entity
@Table(name = "users")
public class User {
  
    @NotNull
    private String username;
    @NotNull
    @Id
    @Email
    private String email;
    @NotNull
    private String password;
    private String profile;
    
    private String rating;

    public User(String name, String email, String pass, String pro, String f) {
        this.username = name;
        this.email = email;
        this.password = pass;
        this.profile = pro;
        this.setRating(f);
    }

    /**
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * @return the profile
     */
    public String getProfile() {
        return profile;
    }

    /**
     * @param profile the profile to set
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    private User() {
    }

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
