package com.sendvi41.borshdesk.websocket;

import com.sendvi41.borshdesk.utils.Tools;
import org.apache.log4j.Logger;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

public class SubscribeClientForPersonalQueue extends Thread{

    private final Logger logger = Logger.getLogger(SubscribeClientForPersonalQueue.class.getName());

    private static String URL = "ws://localhost:8080/websocket-chat";

    private volatile boolean turn = true;

    private String recepientid ;

    public SubscribeClientForPersonalQueue(String recepientid) {
        this.recepientid = recepientid;
    }

    public String getRecepientid() {
        return recepientid;
    }

    public ListenableFuture<StompSession> connect() {


        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        return stompClient.connect(URL, new MyHandler());
    }

    public void subscribeTopic(StompSession stompSession,String recepientid) throws ExecutionException, InterruptedException {
        stompSession.subscribe("/user/" +recepientid + "/queue/messages", new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return Message.class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object payload) {
                Message msg = (Message) payload;
                Tools.addPersonalChat(msg.getSenderid(), msg.getData().getText(), "client");
                logger.info("Received : id" +msg.getSenderid() +" Text " + msg.getData().getText() + " from : " + msg.getAuthor() + " type: " + msg.getType());
            }
        });
        logger.info("Subscribing to topic : " + recepientid);
    }

    private class MyHandler extends StompSessionHandlerAdapter {
        public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
            logger.info("ConsultClient now connected");
        }
    }


    @Override
    public void run() {
        ListenableFuture<StompSession> f = connect();
        try {
            StompSession stompSession = f.get();

            subscribeTopic(stompSession,  getRecepientid());
            logger.info("Subscribing to topic using session " + stompSession);

            while (turn)
            {
                Thread.sleep(1000);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }




    }

    public void shutDown() {
        setTurn(false);
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

}
