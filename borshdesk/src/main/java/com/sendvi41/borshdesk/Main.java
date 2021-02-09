package com.sendvi41.borshdesk;

import com.sendvi41.borshdesk.controllers.FxController;
import com.sendvi41.borshdesk.controllers.StartController;
import com.sendvi41.borshdesk.controllers.WorkController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;


@SpringBootApplication
public class Main extends Application {



    public static void main(String[] args){
         Main.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage workStage = new Stage();

        primaryStage.setResizable(false);

        StartController startController = FxController.init(primaryStage, "/fxml/startScene.fxml");

        WorkController workController =  FxController.init(workStage, "/fxml/workScene.fxml");


    }
//        FXMLLoader loader = new FXMLLoader();
//        URL xmlUrl = getClass().getResource("/fxml/startScene.fxml");
//        loader.setLocation(xmlUrl);
//        Parent root = loader.load();
//
//        primaryStage.setScene(new Scene(root));
//        primaryStage.setResizable(false);
//        primaryStage.show();
//    }

//    public Parent getScene(String fxmlUrl) throws Exception {
//        FXMLLoader loader = new FXMLLoader();
//        URL xmlUrl = getClass().getResource(fxmlUrl);
//        loader.setLocation(xmlUrl);
//
//        Parent root = loader.load();
//
//        return root;
//    }
//
//    public Stage createNewStage(Parent root)
//    {
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//        return stage;
//    }

}