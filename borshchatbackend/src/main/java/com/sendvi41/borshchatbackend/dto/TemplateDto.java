package com.sendvi41.borshchatbackend.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("consultant_id")
    private Long consultant_id;
}
