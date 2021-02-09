package com.sendvi41.borshdesk.controllers;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Getter
@Setter
abstract public class FxController {
    private Stage stage;
    private Scene scene;


    public void initialize(){

    }

    public static <T extends FxController> T init(Stage stage, String source) {
        FXMLLoader fxmlLoader = new FXMLLoader();


        try (InputStream inputStream = fxmlLoader.getClass().getClassLoader().getResourceAsStream(source)) {

            Parent root = fxmlLoader.load(inputStream);

            Scene scene = new Scene(root);

            stage.setScene(scene);

            T controller = fxmlLoader.getController();

            controller.setStage(stage);
            controller.setScene(scene);

            return controller;

        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
            throw new RuntimeException();
        }
    }

}
