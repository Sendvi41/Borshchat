package com.sendvi41.borshdesk.controllers;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;


@Getter
abstract public class FxController {
    private final Stage stage = new Stage();
    private Scene scene;
    private Parent root;


    protected abstract String getSource();

     {
        FXMLLoader fxmlLoader = new FXMLLoader();


        try (InputStream inputStream = fxmlLoader.getClass().getClassLoader().getResourceAsStream(getSource())) {
            fxmlLoader.setControllerFactory(param->this);

            root = fxmlLoader.load(inputStream);

            Scene scene = new Scene(root);

            stage.setScene(scene);


        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }


    public void updateRoot(){
        FXMLLoader fxmlLoader = new FXMLLoader();


        try (InputStream inputStream = fxmlLoader.getClass().getClassLoader().getResourceAsStream(getSource())) {
            fxmlLoader.setControllerFactory(param->this);

            root = fxmlLoader.load(inputStream);

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
