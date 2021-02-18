package com.sendvi41.borshdesk.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class TemplateController extends FxController  {
    private final String source = "fxml/templateScene.fxml";

    @FXML
    private AnchorPane templates;

    @FXML
    private VBox area;

    @Override
    public void init() {
        this.scene
        super.init();
        area.getChildren().setAll();

    }
}
