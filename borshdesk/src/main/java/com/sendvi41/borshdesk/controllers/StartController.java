package com.sendvi41.borshdesk.controllers;

import com.sendvi41.borshdesk.dto.Consultant;
import com.sendvi41.borshdesk.services.Authorization;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Getter
@Component
@RequiredArgsConstructor
public class StartController extends FxController implements CommandLineRunner {
    private final MenuController menuController;
    private final String source = "fxml/startScene.fxml";
    @Autowired
    private Authorization authorization;

    @FXML
    private Button login;

    @FXML
    private TextField log;

    @FXML
    private PasswordField password;

    @FXML
    private Label message;


    @FXML
    private void logIn() {

        Consultant consultant = new Consultant();
        consultant.setName(log.getText());
        consultant.setPassword(password.getText());
        if (authorization.checkLoginAndPassword(consultant)) {
            message.setVisible(false);
            getStage().hide();
            menuController.getStage().showAndWait();

        } else {
            message.setVisible(true);
        }

    }

    @Override
    public void run(String... args) {
        getStage().show();
    }

}
