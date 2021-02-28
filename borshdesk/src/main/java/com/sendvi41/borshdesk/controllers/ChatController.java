package com.sendvi41.borshdesk.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ChatController extends FxController {
    private final String source = "fxml/chatScene.fxml";
}
