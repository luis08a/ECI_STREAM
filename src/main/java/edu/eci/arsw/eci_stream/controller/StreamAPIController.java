package edu.eci.arsw.eci_stream.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.services.Services;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * StreamAPIController
 */
@RestController
public class StreamAPIController {
    Services ss = new Services();

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public ResponseEntity<?> requestMethodName() {
        try {
            return new ResponseEntity<>(ss.consultUsers(),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(StreamAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);            
        }  
    }
    
    @RequestMapping(value="/users/{userID}", method=RequestMethod.GET)
    public ResponseEntity<?> requestMethodName(@RequestParam String User) {
        try{
            return new ResponseEntity<>(ss.consultUsers(), HttpStatus.ACCEPTED)
        } catch (Exception ex) {
            Logger.getLogger(StreamAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);            
        } 
    }
    
    @RequestMapping(value="/users", method=RequestMethod.POST)
    public ResponseEntity<?> LogIn(@RequestBody User o) {
        try{
            ss.createUser(o, "password");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(StreamAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Refussed",HttpStatus.FORBIDDEN);            
        }     }
    
    
}