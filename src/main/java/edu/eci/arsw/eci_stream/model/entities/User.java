package edu.eci.arsw.eci_stream.model.entities;

public class User {
    private String name;
    private String email;
    private String pass; 
    
    public User(String name, String email,String pass){
        this.name=name;
        this.email=email; 
        this.pass=pass;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
    
}
