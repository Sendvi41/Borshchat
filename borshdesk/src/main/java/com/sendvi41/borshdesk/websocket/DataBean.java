package com.sendvi41.borshdesk.websocket;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
public  class DataBean{
    @JsonProperty("text")
    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}