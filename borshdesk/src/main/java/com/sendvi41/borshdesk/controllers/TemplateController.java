package com.sendvi41.borshdesk.controllers;

import com.sendvi41.borshdesk.dto.Template;
import com.sendvi41.borshdesk.services.TemplateService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Getter
@Component
@RequiredArgsConstructor
public class TemplateController extends FxController {
    private final String source = "fxml/templateScene.fxml";


    @Autowired
    private TemplateService templateService;

    @FXML
    private AnchorPane templates;

    @FXML
    private VBox area;

    @FXML
    private TextArea textarea;

    @FXML
    private Button create;

    @FXML
    private Button update;

    @FXML
    private Button delete;


    public void showTemplates(Long id) {
        List<Template> templates = templateService.getTemplates(id);
        List<Label> list = new LinkedList<>();
        for (Template tem : templates) {

            String message = tem.getMessage();
            Label newLabel = new Label(tem.getMessage());

            newLabel.setOnMouseClicked((e)->{
                newLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                showInTextArea(message);
            });

            list.add(newLabel);
        }
        area.getChildren().setAll(list);

    }

    public void showInTextArea(String text){
        textarea.setText(text);
    }

    @Override
    public void init() {

        super.init();


    }
}
