package com.sendvi41.borshdesk;

import com.sendvi41.borshdesk.controllers.FxController;
import com.sendvi41.borshdesk.controllers.StartController;
import com.sendvi41.borshdesk.controllers.WorkController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BorshchatdeskApplication extends Application {
    private static String[] args;


    public static void main(String[] args) {
        BorshchatdeskApplication.args = args;
        BorshchatdeskApplication.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SpringApplication.run(BorshchatdeskApplication.class, args);
    }

//    Stage workStage = new Stage();
//
//        primaryStage.setResizable(false);
//
//    StartController startController = FxController.init(primaryStage, "/fxml/startScene.fxml");
//
//    WorkController workController = FxController.init(workStage, "/fxml/workScene.fxml");
//
//        startController.getStage().show();



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