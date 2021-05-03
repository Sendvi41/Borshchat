package com.sendvi41.borshdesk.websocket;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @JsonProperty("author")
    private String author;


    @JsonProperty("data")
    private DataBean data;

    @JsonProperty("type")
    private String type;


}

