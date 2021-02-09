package com.sendvi41.borshdesk.controllers;

import com.sendvi41.borshdesk.Main;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Getter;


@Getter
public class StartController extends FxController {

    @FXML
    private Button login;


    @FXML
    private void logIn() {
        Main main = new Main();
        try {
            Parent root = main.getScene("/fxml/workScene.fxml");
            Stage stage = main.createNewStage(root);
            login.getScene().getWindow().hide();
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
