package com.sendvi41.borshdesk.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nameclient")
    private String nameclient;

    @JsonProperty("surnameclient")
    private String surnameclient;

    @JsonProperty("patronymicclient")
    private String patronymicclient;

    @JsonProperty("email")
    private String email;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("consultant_id")
    private Long consultant_id;

}
