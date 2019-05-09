package edu.eci.arsw.eci_stream.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.eci_stream.model.entities.Room;
import edu.eci.arsw.eci_stream.model.entities.User;
import edu.eci.arsw.eci_stream.persistence.PersistenceException;
import edu.eci.arsw.eci_stream.services.Services;

/**
 * RoomController
 */
@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    Services ss;
    //Get
    @GetMapping()
    public ResponseEntity<?> consultRooms() {
        try{
            return new ResponseEntity<>(ss.getAllRooms(), HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);            
        } 
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultRoomByID(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(ss.getRoomById(id), HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);            
        } 
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<?> consultUsersInASpecificRoom(@PathVariable Long id) {
        try{
            List<User> data = ss.getUsersByRoom(id);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);            
        } 
    }

    // Post requests
    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createARoom(@RequestBody Room room) {
        try{
            System.out.println(ss.createRoom(room));
            //System.out.println("crear en java aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            return new ResponseEntity<>(ss.createRoom(room), HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Refused to create the room. Invalid information",HttpStatus.FORBIDDEN);            
        }     
    }

    @PostMapping(value="/{roomId}/users", consumes = "application/json")
    public ResponseEntity<?> joinInARoom(@PathVariable Long roomId ,@RequestBody User u) {
        try{
            ss.joinInAroom(u,roomId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }     
    }

    // Uptate
    @PutMapping()
    public ResponseEntity<?> updateRoomInfo(@RequestBody Room room) {
        try{
            return new ResponseEntity<>(ss.getAllRooms(), HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);            
        } 
    }
    // Delete
    @DeleteMapping()
    public ResponseEntity<?> deleteAllRooms() {
        try{
            return new ResponseEntity<>(ss.getAllRooms(), HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);            
        } 
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable long roomId) {
        try{
            return new ResponseEntity<>(ss.getAllRooms(), HttpStatus.ACCEPTED);
        } catch (PersistenceException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);            
        } 
    }
}