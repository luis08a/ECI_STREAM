package edu.eci.arsw.eci_stream.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.eci_stream.model.entities.User;

/**
 * UserPersistence
 */
@Repository
public interface UserPersistence extends CrudRepository<User, Long>{
}