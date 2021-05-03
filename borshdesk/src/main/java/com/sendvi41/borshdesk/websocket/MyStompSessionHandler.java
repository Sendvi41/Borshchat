package com.sendvi41.borshdesk.websocket;


import org.apache.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private final Logger logger = Logger.getLogger(MyStompSessionHandler.class.getName());

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());
        session.subscribe("/topic/user", this);
        logger.info("Subscribed to /topic/user");
        session.send("/app/user-all", getSampleMessage());
        logger.info("Message sent to websocket server");
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
    }


    private Message getSampleMessage() {
        Message msg = new Message();
        msg.setAuthor("me");
        msg.setData(new DataBean("LOL LOL OOLO LOL OL OL OL"));
        msg.setType("text");
        return msg;
    }
}