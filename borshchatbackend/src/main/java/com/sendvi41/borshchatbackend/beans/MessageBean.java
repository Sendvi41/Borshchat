package com.sendvi41.borshchatbackend.beans;


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
public class MessageBean {
    @JsonProperty("author")
    private String author;


    @JsonProperty("data")
    private DataBean data;

    @JsonProperty("type")
    private String type;

    @JsonAutoDetect
    @AllArgsConstructor
    @NoArgsConstructor
    @lombok.Data
    class DataBean{
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
