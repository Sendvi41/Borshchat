package com.sendvi41.borshdesk.controllers;

import com.sendvi41.borshdesk.dto.Template;
import com.sendvi41.borshdesk.services.TemplateService;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
@RequiredArgsConstructor
public class TemplateController extends FxController  {
    private final String source = "fxml/templateScene.fxml";



    @Autowired
    private TemplateService templateService;

    @FXML
    private AnchorPane templates;

    @FXML
    private VBox area;


    public void showTemplates(Long id){
        List<Template> templates = templateService.getTemplates(id);

        for (Template tem: templates) {
            area.getChildren().setAll(new Text(tem.getMessage()));
        }

    }
    @Override
    public void init() {

        super.init();


    }
}
