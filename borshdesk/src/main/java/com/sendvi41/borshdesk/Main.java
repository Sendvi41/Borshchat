package com.sendvi41.borshdesk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/fxml/startScene.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Parent getScene(String fxmlUrl) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource(fxmlUrl);
        loader.setLocation(xmlUrl);

        Parent root = loader.load();

        return root;
    }

    public Stage createNewStage(Parent root)
    {
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        return stage;
    }

}