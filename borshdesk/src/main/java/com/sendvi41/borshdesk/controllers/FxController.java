package com.sendvi41.borshdesk.controllers;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Getter
abstract public class FxController {
    private final Stage stage = new Stage();
    private Scene scene;

    protected abstract String getSource();

     {
        FXMLLoader fxmlLoader = new FXMLLoader();


        try (InputStream inputStream = fxmlLoader.getClass().getClassLoader().getResourceAsStream(getSource())) {
            fxmlLoader.setControllerFactory(param->this);

            Parent root = fxmlLoader.load(inputStream);

            Scene scene = new Scene(root);

            stage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }


    public void initialize(){

    }

    @PostConstruct
    public void init(){}
}