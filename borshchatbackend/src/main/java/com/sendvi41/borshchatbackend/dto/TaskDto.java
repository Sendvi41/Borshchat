package com.sendvi41.borshchatbackend.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

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
