package com.sendvi41.borshdesk.controllers;

import com.sendvi41.borshdesk.utils.ChatMessage;
import com.sendvi41.borshdesk.utils.LabelChat;
import com.sendvi41.borshdesk.utils.Tools;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
public class ChatController extends FxController {
    private final String source = "fxml/chatScene.fxml";

    private static final Logger logger = Logger.getLogger(ChatController.class.getName());

    private Long selectedID = null;

    @FXML
    private VBox areaChats;

    @FXML
    private VBox receivedChats;

    @FXML
    private VBox sendChats;


    public void showAllChats(){
        List<LabelChat> list;
        List<Label> labels = new LinkedList<>();
        list = Tools.getReceivedChats();
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
                newlab.setStyle("-fx-text-fill: red; -fx-font-size: 16px");
                this.selectedID = lc.getId();
                receivedChats.getChildren().clear();
                for( ChatMessage a :lc.getHistory()) {
                    if(a.getAuthor().equals("client"))
                    {
                        receivedChats.getChildren().addAll(new Label (a.getMessage()));
                        Label emptyLabel = new Label();
                        emptyLabel.setVisible(false);
                        sendChats.getChildren().addAll(emptyLabel);
                    }
                    else if(a.getAuthor().equals("consult")){

                        sendChats.getChildren().addAll(new Label (a.getMessage()));
                        Label emptyLabel = new Label();
                        emptyLabel.setVisible(false);
                        receivedChats.getChildren().addAll(emptyLabel);
                    }

                }
//
            });
            labels.add(newlab);
        }
        areaChats.getChildren().setAll(labels);

    }

}
