package com.sendvi41.borshdesk.controllers;

import com.sendvi41.borshdesk.BorshchatdeskApplication;

import com.sendvi41.borshdesk.services.Authorization;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Getter
@Component
@RequiredArgsConstructor
public class StartController extends FxController implements CommandLineRunner {
    private final WorkController workController;
    private final String source = "fxml/startScene.fxml";
    @Autowired
    private Authorization authorization;

    @FXML
    private Button login;


    @FXML
    private void logIn() {

        authorization.checkWS();

         workController.getStage().showAndWait();
         getStage().hide();
    }

    @Override
    public void run(String... args){
        getStage().show();
    }

}
