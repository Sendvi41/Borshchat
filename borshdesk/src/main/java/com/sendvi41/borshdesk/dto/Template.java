package com.sendvi41.borshdesk.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@JsonAutoDetect
public class Template {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("consultant_id")
    private Long consultant_id;

    public Template() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Template;
    }

}