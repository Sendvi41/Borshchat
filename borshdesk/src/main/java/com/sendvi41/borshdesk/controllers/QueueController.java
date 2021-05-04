package com.sendvi41.borshdesk.controllers;


import com.sendvi41.borshdesk.websocket.MyStompSessionHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.sendvi41.borshdesk.websocket.StompClient;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Component
public class QueueController extends FxController {
    private final String source = "fxml/queueScene.fxml";

    private static final Logger logger = Logger.getLogger(QueueController.class.getName());




    static private List<Label> list = new LinkedList<>();

    @FXML
    private VBox area;



    @Getter
    @Setter
    protected class LabelChat {

        private Long id;
        private Label label;
        private List<ChatMessage> history = new LinkedList<>();

        public LabelChat(Long id, Label label) {
            this.id = id;
            this.label = label;
        }

        public void setMessage(String author,String message ) {
            this.history.add(new ChatMessage(author, message));
        }
        protected class ChatMessage{
            private String author;
            private String message;

            public ChatMessage(String author, String message) {
                this.author = author;
                this.message = message;
            }
        }


    }




    static public synchronized void addNewChat(String id, String message){
        logger.info("Enter to addNewChat");
        List<LabelChat> listnode = new LinkedList<>();


        Label newLabel = new Label(id);

        list.add(newLabel);


        logger.info("Set all Labels");
    }


}
