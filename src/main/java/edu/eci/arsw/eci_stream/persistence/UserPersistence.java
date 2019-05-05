package edu.eci.arsw.eci_stream.persistence;

import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.eci_stream.model.entities.User;

/**
 * UserPersistence
 */
@Repository
public interface UserPersistence extends CrudRepository<User, String >{
    List<User> findByusername(String name);
    List<User> findByEmail(String email);


    @Query("select u from User u where u.email=:n and u.password=:p")
    List<User> findUser(@Param("n") String name,@Param("p") String password);
	void updateRating(float r);

  
    
}