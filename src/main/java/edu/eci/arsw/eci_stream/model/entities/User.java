package edu.eci.arsw.eci_stream.model.entities;

public class User {
    private String name;
    private String email;
    
    public User(String name, String email){
        this.name=name;
        this.email=email;
    }

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
    
}
