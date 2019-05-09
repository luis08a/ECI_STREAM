package edu.eci.arsw.eci_stream.controllers;

import edu.eci.arsw.eci_stream.domain.WSChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
@Controller
public class WSController {
    
    @Autowired
    SimpMessagingTemplate msgt;
    
    @MessageMapping("/chat.{id}")
    public void sendMessage(@Payload WSChatMessage webSocketChatMessage, @DestinationVariable String id) {
        msgt.convertAndSend("/topic/chat."+id, webSocketChatMessage);
    }
    
    @MessageMapping("/chat.{id}.newUser")
    public void newUser(@Payload WSChatMessage webSocketChatMessage, SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String id) {        
        headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
        msgt.convertAndSend("/topic/chat."+id, webSocketChatMessage);
    }

}
