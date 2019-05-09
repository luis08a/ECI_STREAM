package edu.eci.arsw.eci_stream.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.persistence.PersistenceException;
import edu.eci.arsw.eci_stream.services.Services;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
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
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> consultAllUsers() {
        try {
            return new ResponseEntity<>(ss.getAllUsers(), HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public ResponseEntity<?> consultUserById(@PathVariable String user) {
        try {
            return new ResponseEntity<>(ss.findById(user), HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }
    /*
     * @RequestMapping( method=RequestMethod.GET) public ResponseEntity<?>
     * consultUsersByName(@RequestAttribute String userName) { try{ return new
     * ResponseEntity<>(ss.findById(userName), HttpStatus.ACCEPTED); } catch
     * (PersistenceException ex) {
     * Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
     * return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND); } }
     */
    // POST requests

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody User u) {
        try {
            System.out.println("registrando");            
            ss.createUser(u);

            return new ResponseEntity<>("Creado",HttpStatus.NO_CONTENT);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Refussed", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value="/rating.{user}", method = RequestMethod.POST)
    public ResponseEntity<?> updateRating(@PathVariable String user,@RequestBody String r) {
        try{
           int f = Integer.valueOf(r);
          
           boolean b =ss.rating(user, f);
            if(b){
                return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
            }
            else{
                return new ResponseEntity<>(ss.findById(user), HttpStatus.ACCEPTED);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.INTERNAL_SERVER_ERROR);            
        }
        
  }

    // Session
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<?> getUserLogged() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getPrincipal().toString();
            return new ResponseEntity<>(ss.findById(name).getEmail(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }
}