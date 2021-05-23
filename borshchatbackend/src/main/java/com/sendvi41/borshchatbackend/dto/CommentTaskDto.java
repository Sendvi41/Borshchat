package com.sendvi41.borshchatbackend.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.entities.TaskClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
public class CommentTaskDto {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("comment")
    private String comment;

    @JoinColumn(name = "consultant_id", nullable = false)
    private Consultant consultant_id;

    @JsonProperty("date")
    private LocalDateTime localDateTime;

    @JsonProperty("task_id")
    private Long task_id;


}
