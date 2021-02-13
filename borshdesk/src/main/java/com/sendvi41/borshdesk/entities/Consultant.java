package com.sendvi41.borshdesk.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonAutoDetect
public class Consultant{


    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;
}