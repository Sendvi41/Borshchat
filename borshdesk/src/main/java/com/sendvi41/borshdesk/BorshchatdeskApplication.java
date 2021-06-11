package com.sendvi41.borshdesk;

import com.sendvi41.borshdesk.websocket.StompClient;
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


}