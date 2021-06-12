package com.sendvi41.borshdesk.controllers;


import com.sendvi41.borshdesk.dto.Template;
import com.sendvi41.borshdesk.utils.ChatMessage;
import com.sendvi41.borshdesk.utils.LabelChat;
import com.sendvi41.borshdesk.utils.Tools;
import com.sendvi41.borshdesk.websocket.ConsultClient;
import com.sendvi41.borshdesk.websocket.MyStompSessionHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sendvi41.borshdesk.websocket.StompClient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
public class QueueController extends FxController {
    private final String source = "fxml/queueScene.fxml";

    private static final Logger logger = Logger.getLogger(QueueController.class.getName());


    private Long selectedID = null;

    private Long userId;




    @FXML
    private VBox area;

    @FXML
    private VBox received;

    @FXML
    private Button acceptchat;

    @Override
    public void initialize() {
        super.initialize();
        acceptchat.setTooltip(new Tooltip("hotkey: CTRL + R"));
    }

    public void showHistoryChat(){

    }

    @FXML
    private void takeChat() throws IOException {
        Tools.deleteChatFromMainQueue(selectedID.toString());
        ConsultClient thread = new ConsultClient(userId.toString(), selectedID.toString());
        thread.start();
        ChatController.addThread(thread);

        received.getChildren().clear();
        selectedID = null;
        showAllChats();
    }



    public void takeFirstChat() {
        List<LabelChat> list;
        list = Tools.getListChats();
        if(list.size()!=0){

            Tools.deleteChatFromMainQueue(list.get(0).getId().toString());
            ConsultClient thread = new ConsultClient(userId.toString(), list.get(0).getId().toString());
            thread.start();
            ChatController.addThread(thread);

            received.getChildren().clear();
            selectedID = null;
            showAllChats();
        }

    }






    public void showAllChats(){
        List<LabelChat> list;
        List<Label> labels = new LinkedList<>();
        list = Tools.getListChats();
        List<Label> messages = new LinkedList<>();
        for (LabelChat lc : list) {
            Label newlab = lc.getLabel();
            newlab.setOnMouseClicked((e) -> {
                if (getSelectedID() != null) {
                    List<LabelChat> result = list.stream()
                            .filter(item -> item.getId().equals(getSelectedID()))
                            .collect(Collectors.toList());
                    Label res = result.get(0).getLabel();
                    res.setStyle("");

                }
                newlab.setStyle("-fx-text-fill: white; -fx-font-size: 16px");
                this.selectedID = lc.getId();
                received.getChildren().clear();
               for( ChatMessage a :lc.getHistory()) {
                    if(a.getAuthor()=="client")
                    {
                        Label newlabel = new Label(a.getMessage());
                        newlabel.setWrapText(true);
                        newlabel.prefWidthProperty().bind(received.widthProperty());
                        received.getChildren().addAll(newlabel);
//                        messages.add();
                    }

                }
//                .setAll(messages);
            });
            labels.add(newlab);
        }
        area.getChildren().setAll(labels);

    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public void fireAcceptChat(){
        acceptchat.fire();
    }
    @Override
    public void init() {
        super.init();


    }
}
