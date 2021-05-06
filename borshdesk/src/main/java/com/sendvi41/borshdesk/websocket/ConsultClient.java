package com.sendvi41.borshdesk.websocket;

import org.apache.log4j.Logger;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ConsultClient implements Runnable{

    private final Logger logger = Logger.getLogger(ConsultClient.class.getName());

    private final static WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    private static String URL = "ws://localhost:8080/websocket-chat";

    private volatile boolean turn = true;

    private volatile String message;

    public void sendMessageToClient(String message) {
        this.message = message;
    }

    private String recepientid ;
    private String senderid;

    public ConsultClient() {

    }

    public ConsultClient(String recepientid, String senderid) {
        this.recepientid = recepientid;
        this.senderid = senderid;
    }

    public String getRecepientid() {
        return recepientid;
    }

    public String getSenderid() {
        return senderid;
    }

    public ListenableFuture<StompSession> connect() {

//        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
//        List<Transport> transports = Collections.singletonList(webSocketTransport);
//
//        SockJsClient sockJsClient = new SockJsClient(transports);
//
//        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());
//
//        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());


//        stompClient.connect(URL, new MyHandler());

//        return stompClient.connect(URL, headers, new MyHandler());

        return stompClient.connect(URL, new MyHandler());
    }

    public void subscribeTopic(StompSession stompSession,String recepientid) throws ExecutionException, InterruptedException {
        stompSession.subscribe("/user/" +recepientid + "/queue/messages", new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return Message.class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object payload) {
                Message msg = (Message) payload;
                logger.info("Received : " + msg.getData().getText() + " from : " + msg.getAuthor() + " from : " + msg.getType());
            }
        });
        logger.info("Subscribing to topic : " + recepientid);
    }

    public void sendMessage(StompSession stompSession, String text) {

        Message msg = new Message();
        msg.setAuthor("them");
        msg.setRecipientid(getRecepientid());
        msg.setType("text");
        msg.setSenderid(getSenderid());
        msg.setData(new DataBean(text));
        stompSession.send("/app/chat", msg);
        logger.info("Message was send : " + msg.getData().getText());

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

            sendMessage(stompSession, "Hello");

            while (turn)
            {
                Thread.sleep(50000);
                if(this.message!=null){
                    sendMessage(stompSession, this.message);
                    this.message = null;
                }

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

