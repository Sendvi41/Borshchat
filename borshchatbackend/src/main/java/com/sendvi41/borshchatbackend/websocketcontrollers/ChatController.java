package com.sendvi41.borshchatbackend.websocketcontrollers;

import com.sendvi41.borshchatbackend.beans.MessageBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/user-all")
    @SendTo("/user/queue")
    public MessageBean send(@Payload MessageBean message) {
        System.out.println("Enter to method send in ChatController");
        System.out.println(message);
        return message;
    }

    @Autowired private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chat")
    public void processMessage(@Payload MessageBean Message) {

        System.out.println(Message);
        messagingTemplate.convertAndSendToUser(
                Message.getSenderid(),"/queue/messages",
                Message);
        System.out.println(messagingTemplate.getUserDestinationPrefix());
    }

    @MessageMapping("/chatdesk")
    public void processMessageDesk(@Payload MessageBean Message) {

        System.out.println(Message);
        messagingTemplate.convertAndSendToUser(
                Message.getRecipientid(),"/queue/messages",
                Message);
        System.out.println(messagingTemplate.getUserDestinationPrefix());
    }

}