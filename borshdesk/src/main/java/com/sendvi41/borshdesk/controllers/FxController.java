package com.sendvi41.borshdesk.controllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Getter
@Setter
abstract public class FxController {
    private Stage stage;
    private Scene scene;

    public static <T extends FxController> T init(Stage stage, String source) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();

        try (InputStream inputStream = fxmlLoader.getClass().getClassLoader().getResourceAsStream(source)) {

            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);

            stage.setScene(scene);

            T controller = fxmlLoader.getController();

            controller.setStage(stage);
            controller.setScene(scene);

            return controller;

        }


    }


}
