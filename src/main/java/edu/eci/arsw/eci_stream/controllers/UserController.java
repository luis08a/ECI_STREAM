package edu.eci.arsw.eci_stream.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.eci_stream.model.entities.Room;
import edu.eci.arsw.eci_stream.model.entities.RoomInfo;
import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.persistence.PersistenceException;
import edu.eci.arsw.eci_stream.services.Services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * StreamAPIController
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    Services ss;

    // GET requests
    @RequestMapping( method=RequestMethod.GET)
    public ResponseEntity<?> consultAllUsers() {
        try {
            return new ResponseEntity<>(ss.getAllUsers(),HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);            
        }  
    }
    
    @RequestMapping(value="/{user}", method=RequestMethod.GET)
    public ResponseEntity<?> consultUserByName(@PathVariable String user) {
        try{
            return new ResponseEntity<>(ss.consultUserByName(user), HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);            
        } 
    }

    // POST requests
    
    @RequestMapping(method=RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody User o) {       
        try{
            ss.createUser(o, o.getPassword());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Refussed",HttpStatus.FORBIDDEN);            
        }     
    }
   
    //Session
    @RequestMapping(value="/me", method = RequestMethod.GET)
    public ResponseEntity<?> getUserLogged() {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            return new ResponseEntity<>(name, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);            
        } 
  }
}