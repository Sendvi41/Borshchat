package com.sendvi41.borshdesk.controllers;

import com.sendvi41.borshdesk.dto.Template;
import com.sendvi41.borshdesk.services.TemplateService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
public class TemplateController extends FxController {
    private final String source = "fxml/templateScene.fxml";
    private Long userId;
    private Long selectedID = null;

    public TemplateController() {
    }

    @Getter
    @Setter
    protected class LabelNode {
        private Long id;
        private Label label;

        public LabelNode(Long id, Label label) {
            this.id = id;
            this.label = label;
        }

    }


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
        List<LabelNode> listnode = new LinkedList<>();
        for (Template tem : templates) {

            String message = tem.getMessage();
            Label newLabel = new Label(tem.getMessage());
            listnode.add(new LabelNode(tem.getId(), newLabel));

            newLabel.setOnMouseClicked((e) -> {
                if (getSelectedID() != null) {
                    List<LabelNode> result = listnode.stream()
                            .filter(item -> item.getId().equals(getSelectedID()))
                            .collect(Collectors.toList());
                    Label res = result.get(0).getLabel();
                    res.setStyle("");

                }
                this.selectedID = tem.getId();
                newLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px");
                showInTextArea(message);
            });

            list.add(newLabel);
        }
        area.getChildren().setAll(list);
    }

    @FXML
    private void createTemplate() {
        if (templateService.createTemplate(textarea.getText(), getUserId())) {
            showTemplates(getUserId());
        } else {
            System.out.println("Шаблон не был создан");
        }

    }

    @FXML
    private void deleteTemplate() {
        if (getSelectedID() != null) {
            if (templateService.deleteTemplate(getSelectedID())) {
                showTemplates(getUserId());
                setSelectedID(null);
            } else {
                System.out.println("Шаблон не был удален");
            }
        } else {
            System.out.println("Шаблон не был выбран");
        }

    }


    public void showInTextArea(String text) {
        textarea.setText(text);
    }

    @Override
    public void init() {

        super.init();


    }

}
