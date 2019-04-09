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
    @Query("select exists(select 1 from users WHERE username=(?) and password = (?))")
    List<User> findByusernameAndpassword(@Param("username") String name,@Param("password") String password);
}