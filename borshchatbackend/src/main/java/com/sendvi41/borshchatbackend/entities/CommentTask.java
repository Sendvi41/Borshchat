package com.sendvi41.borshchatbackend.entities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@JsonAutoDetect
public class CommentTask {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence_com")
    @SequenceGenerator(name = "id_Sequence_com", sequenceName = "ID_SEQ_COMMENT")
    @JsonProperty("id")
    private Long id;


    @Column(name = "comment", nullable = false)
    @JsonProperty("comment")
    private String comment;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "consultant_id", nullable = false)
    private Consultant consultant_id;

    @Column(name = "date")
    @JsonProperty("date")
    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    private TaskClient task_id;
}
