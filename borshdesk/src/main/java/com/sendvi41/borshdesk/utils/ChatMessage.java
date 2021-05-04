package com.sendvi41.borshdesk.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage{
    private String author;
    private String message;

    public ChatMessage(String author, String message) {
        this.author = author;
        this.message = message;
    }
}
