package com.sendvi41.borshchatbackend.entities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
@JsonAutoDetect
public class CommentTask {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ_COMMENT")
    @JsonProperty("id")
    private Long id;


    @Column(name = "comment", nullable = false)
    @JsonProperty("comment")
    private String comment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultant_id", nullable = false)
    @JsonIgnore
    private Consultant consultant_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    private TaskClient task_id;
}
