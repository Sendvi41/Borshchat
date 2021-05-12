package com.sendvi41.borshchatbackend.entities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Data
@JsonAutoDetect
public class TaskClient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ_TASK")
    @JsonProperty("id")
    private Long id;

    @Column(name = "nameclient", nullable = false)
    @JsonProperty("nameclient")
    private String nameclient;

    @Column(name = "surnameclient", nullable = false)
    @JsonProperty("surnameclient")
    private String surnameclient;

    @Column(name = "patronymicclient", nullable = false)
    @JsonProperty("patronymicclient")
    private String patronymicclient;

    @Column(name = "email", nullable = false, unique = true)
    @JsonProperty("email")
    private String email;

    @Column(name = "comment", nullable = false)
    @JsonProperty("comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultant_id", nullable = false)
    @JsonIgnore
    private Consultant consultant_id;

    @Column(name = "tracker", nullable = false)
    @JsonProperty("tracker")
    private String tracker;

    @Column(name = "priority", nullable = false)
    @JsonProperty("priority")
    private String priority;

    @Column(name = "status",  columnDefinition = "varchar(255) default 'New'")
    @JsonProperty("status")
    private String status;

    @Column(name = "theme", nullable = false)
    @JsonProperty("theme")
    private String theme;

    @Column(name = "date",  columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonProperty("date")
    private LocalDateTime localDateTime;;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task_id")
    private Set<CommentTask> commentTasks;





}
