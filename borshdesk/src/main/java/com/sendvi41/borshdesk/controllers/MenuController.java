package com.sendvi41.borshdesk.controllers;


import com.sendvi41.borshdesk.services.Authorization;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Getter
@Component
@RequiredArgsConstructor
public class MenuController extends FxController {
    private final String source = "fxml/menuScene.fxml";


    @Autowired
    private TemplateController templateController;

    @FXML
    private AnchorPane view;


    @FXML
    private void showTemplate() throws IOException {
        AnchorPane target = FXMLLoader.load(getClass().getResource("/"+templateController.getSource()));
        view.getChildren().setAll(target);

    }
}
