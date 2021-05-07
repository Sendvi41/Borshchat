package com.sendvi41.borshdesk.websocket;


import com.sendvi41.borshdesk.controllers.QueueController;
import com.sendvi41.borshdesk.utils.Tools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private final Logger logger = Logger.getLogger(MyStompSessionHandler.class.getName());
    StompSession session;



    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        this.session = session;
        logger.info("New session established : " + session.getSessionId());
        session.subscribe("/topic/queue", this);
        logger.info("Subscribed to /topic/user");
//        session.send("/app/user-all", getSampleMessage());
//        logger.info("Message sent to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Got an exception", exception);

    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message msg = (Message) payload;
        logger.info("Received : " + msg.getData().getText() + " from : " + msg.getAuthor() + " from : " + msg.getType() );
        Tools.addNewChat(msg.getSenderid(), msg.getData().getText());
//        this.session.subscribe("/user/" + 2 + "/queue/messages", this);
//        this.session.send("/app/chat", getSampleMessage());
//        logger.info("Message sent to websocket server");
    }


    private Message getSampleMessage() {
        Message msg = new Message();
        msg.setAuthor("me");
        msg.setData(new DataBean("LOL LOL OOLO LOL OL OL OL"));
        msg.setType("text");
        return msg;
    }
}