package com.sendvi41.borshchatbackend.websocketcontrollers;

import com.sendvi41.borshchatbackend.beans.MessageBean;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/user-all")
    @SendTo("/topic/user")
    public MessageBean send(@Payload MessageBean message) {
        System.out.println("Enter to method send in ChatController");
        System.out.println(message);
        return message;
    }
}