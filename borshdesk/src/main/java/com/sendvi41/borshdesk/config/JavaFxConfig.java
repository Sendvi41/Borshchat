package com.sendvi41.borshdesk.config;


import com.sendvi41.borshdesk.controllers.FxController;
import com.sendvi41.borshdesk.controllers.StartController;
import com.sendvi41.borshdesk.controllers.WorkController;
import javafx.stage.Stage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaFxConfig {


    @Bean
    public WorkController workController(){
        return FxController.init(new Stage(), "fxml/workScene.fxml");
    }

    @Bean
    public StartController startController(){
        return FxController.init(new Stage(), "fxml/startScene.fxml");
    }




}
