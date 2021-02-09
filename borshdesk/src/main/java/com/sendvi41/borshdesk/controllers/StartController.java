package com.sendvi41.borshdesk.controllers;

import com.sendvi41.borshdesk.BorshchatdeskApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.PostConstruct;


@Getter
public class StartController extends FxController implements CommandLineRunner {

    @Autowired
    private WorkController workController;

    @FXML
    private Button login;


    @FXML
    private void logIn() {
         workController.getStage().showAndWait();
         getStage().hide();

    }


    @Override
    public void run(String... args){
        getStage().show();
    }

}
