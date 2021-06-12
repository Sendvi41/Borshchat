package com.sendvi41.borshdesk.controllers;

import com.sendvi41.borshdesk.dto.Template;
import com.sendvi41.borshdesk.services.TaskServiceInterface;
import com.sendvi41.borshdesk.services.TemplateServiceInterface;
import com.sendvi41.borshdesk.utils.ChatMessage;
import com.sendvi41.borshdesk.utils.LabelChat;
import com.sendvi41.borshdesk.utils.Tools;
import com.sendvi41.borshdesk.websocket.ConsultClient;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import javafx.event.EventHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
public class ChatController extends FxController {
    private final String source = "fxml/chatScene.fxml";

    private static final Logger logger = Logger.getLogger(ChatController.class.getName());

    private static List<ConsultClient> threadList = new LinkedList<>();

    ObservableList<String> trackers = FXCollections.observableArrayList("Bug", "Support");
    ObservableList<String> priorities = FXCollections.observableArrayList("High", "Normal", "Low", "Immediate");


    private Long selectedID = null;
    private Long userId;


    private List<Template> listTemplates = new LinkedList<>();

    @Autowired
    private TaskServiceInterface taskService;

    private Popup popup;

    private int size;



    @FXML
    private Button send;

    @FXML
    private Button createtask;

    @FXML
    private SplitPane firstsplit;


    @FXML
    private ScrollPane leftpane;


    @FXML
    private AnchorPane taskarea;


    @FXML
    private AnchorPane buttonarea;

    @FXML
    private AnchorPane chatarea;

    @FXML
    private VBox areaChats;

    @FXML
    private VBox receivedChats;

    @FXML
    private VBox sendChats;

    @FXML
    private TextArea textarea;

    /// for creating task
    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField patronymic;

    @FXML
    private TextField email;

    @FXML
    private TextArea comment;

    @FXML
    private TextField theme;

    @FXML
    private ChoiceBox priority;

    @FXML
    private ChoiceBox tracker;

    @Override
    public void initialize() {
        super.initialize();

        receivedChats.prefWidthProperty().bind(chatarea.widthProperty().divide(2));
        sendChats.prefWidthProperty().bind(chatarea.widthProperty().divide(2));

        receivedChats.maxWidthProperty().bind(chatarea.widthProperty().divide(2));
        sendChats.maxWidthProperty().bind(chatarea.widthProperty().divide(2));

        receivedChats.setSpacing(5.0);
        sendChats.setSpacing(5.0);

        Thread RefreshChats = new Thread() {
            public volatile BooleanProperty updateChat;

            public void run() {
                updateChat =  new SimpleBooleanProperty(false);
                updateChat.addListener(new ChangeListener<Boolean>() {

                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        System.out.println("changed " + oldValue + "->" + newValue);
                        if(newValue){
                            List<LabelChat> list;
                            list = Tools.getReceivedChats();
                            if (selectedID != null) {
                                List<LabelChat> result = list.stream()
                                        .filter(item -> item.getId().equals(getSelectedID()))
                                        .collect(Collectors.toList());
                                LabelChat current = result.get(0);
                                receivedChats.getChildren().clear();
                                sendChats.getChildren().clear();

                                for (ChatMessage a : current.getHistory()) {
                                    if (a.getAuthor().equals("client")) {
                                        Label newlabel = new Label(a.getMessage());
                                        newlabel.setWrapText(true);
                                        newlabel.prefWidthProperty().bind(receivedChats.widthProperty());
                                        receivedChats.getChildren().addAll(newlabel);
                                        Label emptyLabel = new Label(a.getMessage());
                                        emptyLabel.prefWidthProperty().bind(sendChats.widthProperty());
                                        emptyLabel.setVisible(false);
                                        sendChats.getChildren().addAll(emptyLabel);
                                    } else if (a.getAuthor().equals("consult")) {

                                        Label newlabel = new Label(a.getMessage());
                                        newlabel.setWrapText(true);
                                        newlabel.prefWidthProperty().bind(sendChats.widthProperty());
                                        sendChats.getChildren().addAll(newlabel);
                                        Label emptyLabel = new Label(a.getMessage());
                                        emptyLabel.prefWidthProperty().bind(receivedChats.widthProperty());
                                        emptyLabel.setVisible(false);
                                        receivedChats.getChildren().addAll(emptyLabel);
                                    }

                                }
                            }
                            updateChat.setValue(false);
                        }

                    }
                });



                try {
                    while (true) {
                        Thread.sleep(100);
                        if (Tools.updateCurrentChat.getValue()) {
                            Platform.runLater(()->{
                                try{
                                    updateChat.setValue(true);
                                    Tools.updateCurrentChat.setValue(false);
                                }
                                catch (Exception ex){
                                    logger.error(ex);
                                }
                            });

                        }

                    }

                } catch (InterruptedException v) {
                    logger.error(v);
                }
            }

        };
        RefreshChats.start();


        priority.setItems(priorities);
        tracker.setItems(trackers);
        this.listTemplates = Tools.getListTemplates();

        firstsplit.setDividerPositions(0.4);
        leftpane.minWidthProperty().bind(firstsplit.widthProperty().multiply(0.4));
        leftpane.maxWidthProperty().bind(firstsplit.widthProperty().multiply(0.4));


        chatarea.layoutXProperty().addListener((InvalidationListener) observable -> {
            updateSizePopup();
        });
        chatarea.widthProperty().addListener((InvalidationListener) observable -> {
            updateSizePopup();
        });

        chatarea.heightProperty().addListener((InvalidationListener) observable -> {
            updateSizePopup();
        });
//        taskarea.widthProperty();

        textarea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                List<String> templates = new LinkedList<>();

                for (Template tem : listTemplates) {
                    if (tem.getMessage().matches(newValue + "(.*)")) {
                        templates.add(tem.getMessage());
                    }
                }
                if (templates.size() != 0 && textarea.getText().trim().length()!=0) {
                    ObservableList<String> templist = FXCollections.observableArrayList(templates);
                    ListView<String> langsListView = new ListView<String>(templist);

                    MultipleSelectionModel<String> langsSelectionModel = langsListView.getSelectionModel();
                    langsListView.setPrefSize(370, 20 * templates.size());
                    int size = 20 * templates.size();
                    langsListView.prefWidthProperty().bind(textarea.widthProperty());

                    Parent vbox = new VBox(langsListView);


                    Popup popup = new Popup();

                    langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {
                            textarea.setText(newValue);
                            hidePopUp();
                        }
                    });

                    popup.getContent().add(vbox);
                    setSize(size);
                    showPoup(popup);
                }else{
                    hidePoup();
                }
            }
        });

    }

    public void setSize(int size) {
        this.size = size;
    }

    public void updateSizePopup() {
        if (this.popup != null) {
            this.popup.setX(chatarea.localToScreen(0, 0).getX());
            this.popup.setY(chatarea.localToScreen(0, 0).getY() + chatarea.getHeight() - buttonarea.getHeight() - this.size);
        }
    }


    public void hidePoup(){
        if (this.popup != null) {
            this.popup.hide();
        }
    }
    public void showPoup(Popup popup) {
        if (this.popup != null) {
            this.popup.hide();
        }
        this.popup = popup;


        this.popup.show(chatarea, chatarea.localToScreen(0, 0).getX()
                , chatarea.localToScreen(0, 0).getY() + chatarea.getHeight() - buttonarea.getHeight() - this.size);
    }

    public void hidePopUp() {
        this.popup.hide();
    }


    public static void addThread(ConsultClient thread) {
        threadList.add(thread);
    }


    @FXML
    private void send() {
        if (!textarea.getText().trim().equals("")) {

            List<ConsultClient> result = threadList.stream()
                    .filter(item -> item.getSenderid().equals(getSelectedID().toString()))
                    .collect(Collectors.toList());
            ConsultClient res = result.get(0);
            res.sendMessageToClient(textarea.getText());
            Tools.addPersonalChat(res.getSenderid(), textarea.getText(), "consult");

            sendChats.getChildren().addAll(new Label(textarea.getText()));
            Label emptyLabel = new Label();
            emptyLabel.setVisible(false);
            receivedChats.getChildren().addAll(emptyLabel);

            textarea.clear();
        }
    }


    public void showAllChats() {
        taskarea.setVisible(selectedID != null);
        List<LabelChat> list;
        List<Label> labels = new LinkedList<>();
        list = Tools.getReceivedChats();
        if (selectedID != null) {
            List<LabelChat> result = list.stream()
                    .filter(item -> item.getId().equals(getSelectedID()))
                    .collect(Collectors.toList());
            LabelChat current = result.get(0);
            receivedChats.getChildren().clear();
            sendChats.getChildren().clear();

            for (ChatMessage a : current.getHistory()) {
                if (a.getAuthor().equals("client")) {
                    Label newlabel = new Label(a.getMessage());
                    newlabel.setWrapText(true);
                    newlabel.prefWidthProperty().bind(receivedChats.widthProperty());
                    receivedChats.getChildren().addAll(newlabel);
                    Label emptyLabel = new Label(a.getMessage());
                    emptyLabel.setVisible(false);
                    emptyLabel.prefWidthProperty().bind(sendChats.widthProperty());
                    sendChats.getChildren().addAll(emptyLabel);
                } else if (a.getAuthor().equals("consult")) {

                    Label newlabel = new Label(a.getMessage());
                    newlabel.setWrapText(true);
                    newlabel.prefWidthProperty().bind(sendChats.widthProperty());
                    sendChats.getChildren().addAll(newlabel);
                    Label emptyLabel = new Label(a.getMessage());
                    emptyLabel.setVisible(false);
                    emptyLabel.prefWidthProperty().bind(receivedChats.widthProperty());
                    receivedChats.getChildren().addAll(emptyLabel);
                }

            }
        }

        for (LabelChat lc : list) {
            Label newlab = lc.getLabel();
            newlab.setOnMouseClicked((e) -> {
                taskarea.setVisible(true);
                if (getSelectedID() != null) {

                    List<LabelChat> result = list.stream()
                            .filter(item -> item.getId().equals(getSelectedID()))
                            .collect(Collectors.toList());
                    Label res = result.get(0).getLabel();
                    res.setStyle("");
                }
                newlab.setStyle("-fx-text-fill: white; -fx-font-size: 16px");
                this.selectedID = lc.getId();
                Tools.setSelectedID(this.selectedID);
                receivedChats.getChildren().clear();
                sendChats.getChildren().clear();
                for (ChatMessage a : lc.getHistory()) {
                    if (a.getAuthor().equals("client")) {
                        Label newlabel = new Label(a.getMessage());
                        newlabel.setWrapText(true);
                        newlabel.prefWidthProperty().bind(receivedChats.widthProperty());
                        receivedChats.getChildren().addAll(newlabel);
                        Label emptyLabel = new Label(a.getMessage());
                        emptyLabel.setVisible(false);
                        emptyLabel.prefWidthProperty().bind(sendChats.widthProperty());
                        sendChats.getChildren().addAll(emptyLabel);
                    } else if (a.getAuthor().equals("consult")) {
                        Label newlabel = new Label(a.getMessage());
                        newlabel.setWrapText(true);
                        newlabel.prefWidthProperty().bind(sendChats.widthProperty());
                        sendChats.getChildren().addAll(newlabel);
                        Label emptyLabel = new Label(a.getMessage());
                        emptyLabel.setVisible(false);
                        emptyLabel.prefWidthProperty().bind(receivedChats.widthProperty());
                        receivedChats.getChildren().addAll(emptyLabel);
                    }

                }
//
            });
            labels.add(newlab);
        }
        areaChats.getChildren().setAll(labels);

    }

    @FXML
    private void createTask() {
        taskService.createTask(name.getText(), surname.getText(), patronymic.getText(),
                email.getText(), comment.getText(), getUserId(), theme.getText(),
                tracker.getValue().toString(), priority.getValue().toString());
    }


    public void fireSend(){
        send.fire();
    }

    public void fireCreateTask(){
        createtask.fire();
    }


    @Override
    public void init() {
        taskarea.setVisible(false);



    }
}
