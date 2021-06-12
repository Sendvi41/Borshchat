package com.sendvi41.borshdesk.controllers;


import com.sendvi41.borshdesk.dto.Consultant;
import com.sendvi41.borshdesk.services.Authorization;
import com.sendvi41.borshdesk.websocket.StompClient;
import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class MenuController extends FxController {
    private final String source = "fxml/menuScene.fxml";
    private final Logger logger = Logger.getLogger(MenuController.class.getName());
    Consultant currentConsultant;


    @Autowired
    private Authorization authorization;


    @Autowired
    private TemplateController templateController;

    @Autowired
    private ChatController chatController;

    @Autowired
    private QueueController queueController;



    private ImageView currentButton;


    @FXML
    private Button ChatButtonKey;

    @FXML
    private Button TemplateButtonKey;

    @FXML
    private Button QueueButtonKey;

    @FXML
    private ImageView QueueButton;

    @FXML
    private ImageView ChatButton;

    @FXML
    private ImageView TemplateButton;

    @FXML
    private AnchorPane view;


    @Override
    public void initialize() {
        super.initialize();


    }

    @FXML
    private void showTemplate() throws IOException {
        if(currentButton!=null)
        currentButton.setEffect(null);

        ColorAdjust glow = new ColorAdjust();
        glow.setBrightness(1.0);

        TemplateButton.setEffect(glow);
        currentButton=TemplateButton;

        templateController.updateRoot();
        templateController.setUserId(currentConsultant.getId());
        templateController.showTemplates(currentConsultant.getId());
        view.getChildren().setAll(templateController.getRoot().getChildrenUnmodifiable());


    }

    @FXML
    private void showChat() throws IOException {
        if(currentButton!=null)
        currentButton.setEffect(null);

        ColorAdjust glow = new ColorAdjust();
        glow.setBrightness(1.0);

        ChatButton.setEffect(glow);

        currentButton=ChatButton;

        chatController.updateRoot();
        chatController.setUserId(currentConsultant.getId());
        view.getChildren().setAll(chatController.getRoot().getChildrenUnmodifiable());
        chatController.showAllChats();

    }

    @FXML
    private void showQueue() throws IOException {
        if(currentButton!=null)
        currentButton.setEffect(null);

        ColorAdjust glow = new ColorAdjust();
        glow.setBrightness(1.0);

        QueueButton.setEffect(glow);
        currentButton=QueueButton;

        queueController.updateRoot();
        queueController.setUserId(currentConsultant.getId());
        view.getChildren().setAll(queueController.getRoot().getChildrenUnmodifiable());
        queueController.showAllChats();


//        StompClient.startConnect();


    }

    @Override
    public void init() {
        this.getStage().xProperty().addListener((InvalidationListener) observable -> {
            chatController.updateSizePopup();
        });

        this.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.DIGIT1,
                    KeyCombination.CONTROL_DOWN);
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                    QueueButtonKey.fire();
                    logger.info("Key Pressed: " + keyComb);
                    ke.consume();
                }
            }
        });
        this.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.DIGIT2,
                    KeyCombination.CONTROL_DOWN);
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                    ChatButtonKey.fire();
                    logger.info("Key Pressed: " + keyComb);
                    ke.consume();
                }
            }
        });


        this.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.DIGIT3,
                    KeyCombination.CONTROL_DOWN);
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                    TemplateButtonKey.fire();
                    logger.info("Key Pressed: " + keyComb);
                    ke.consume();
                }
            }
        });

        this.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.ENTER,
                    KeyCombination.CONTROL_DOWN);
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                    chatController.fireSend();
                    logger.info("Key Pressed: " + keyComb);
                    ke.consume();
                }
            }
        });

        this.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.D,
                    KeyCombination.CONTROL_DOWN);
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                    chatController.fireCreateTask();
                    logger.info("Key Pressed: " + keyComb);
                    ke.consume();
                }
            }
        });
        this.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(KeyCode.R,
                    KeyCombination.CONTROL_DOWN);
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                    queueController.takeChat();
                    logger.info("Key Pressed: " + keyComb);
                    ke.consume(); // <-- stops passing the event to next node
                }
            }
        });



    }
}
