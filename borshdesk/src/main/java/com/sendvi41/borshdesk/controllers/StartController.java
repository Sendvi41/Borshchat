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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Getter
@Component
@RequiredArgsConstructor
public class StartController extends FxController implements CommandLineRunner {
    private final MenuController menuController;
    private final String source = "fxml/startScene.fxml";
    private final Logger logger = Logger.getLogger(StartController.class.getName());
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
            Consultant currentConsultant = authorization.getConsultant(consultant);
            logger.info("consultant successfully received with id" + currentConsultant.getId());

            menuController.setCurrentConsultant(currentConsultant);
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
