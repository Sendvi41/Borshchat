package com.sendvi41.borshdesk.controllers;


import com.sendvi41.borshdesk.dto.Template;
import com.sendvi41.borshdesk.utils.LabelChat;
import com.sendvi41.borshdesk.utils.Tools;
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






    @FXML
    private VBox area;



    public void showAllChats(){
        List<LabelChat> list;
        List<Label> labels = new LinkedList<>();
        list = Tools.getListChats();

        for (LabelChat lc : list) {
           labels.add(lc.getLabel());
        }
        area.getChildren().setAll(labels);

    }







}
