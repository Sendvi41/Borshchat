package com.sendvi41.borshdesk.utils;

import com.sendvi41.borshdesk.controllers.QueueController;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class LabelChat {

    private Long id;
    private Label label;
    private List<ChatMessage> history = new LinkedList<>();

    public LabelChat(Long id, Label label) {
        this.id = id;
        this.label = label;
    }

    public void setMessage(String author,String message ) {
        this.history.add(new ChatMessage(author, message));
    }
    protected class ChatMessage{
        private String author;
        private String message;

        public ChatMessage(String author, String message) {
            this.author = author;
            this.message = message;
        }
    }


}
