package com.sendvi41.borshdesk.controllers;


import com.sendvi41.borshdesk.dto.Consultant;
import com.sendvi41.borshdesk.services.Authorization;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class MenuController extends FxController {
    private final String source = "fxml/menuScene.fxml";
    private final Logger logger = Logger.getLogger(MenuController.class.getName());
    Consultant currentConsultant;

    @Autowired
    private Authorization authorization;


    @Autowired
    private TemplateController templateController;

    @FXML
    private AnchorPane view;


    @FXML
    private void showTemplate() throws IOException {
        templateController.setUserId(currentConsultant.getId());
        templateController.showTemplates(currentConsultant.getId());
        view.getChildren().setAll(templateController.getRoot().getChildrenUnmodifiable());

    }

    @Override
    public void init() {


    }
}
