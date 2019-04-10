package edu.eci.arsw.eci_stream.controller;

import edu.eci.arsw.eci_stream.domain.WSChatMessage;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@Service
@Controller
public class WSChatController {
    
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/javainuse")
    public WSChatMessage sendMessage(@Payload WSChatMessage webSocketChatMessage) {
        return webSocketChatMessage;
    }
    
    @MessageMapping("/chat.newUser")
    @SendTo("/topic/javainuse")
    public WSChatMessage newUser(@Payload WSChatMessage webSocketChatMessage,
        SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
        return webSocketChatMessage;
    }
}
