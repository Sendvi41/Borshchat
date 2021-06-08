package com.sendvi41.borshchatbackend.entities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Data
@JsonAutoDetect
public class TaskClient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence_Task")
    @SequenceGenerator(name = "id_Sequence_Task", sequenceName = "ID_SEQ_TASK")
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

    @Column(name = "email", nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(name = "comment", nullable = false)
    @JsonProperty("comment")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "consultant_id", nullable = false)
//    @JsonIgnore
    private Consultant consultant_id;

    @Column(name = "tracker", nullable = false)
    @JsonProperty("tracker")
    private String tracker;

    @Column(name = "priority", nullable = false)
    @JsonProperty("priority")
    private String priority;

    @Column(name = "status")
    @JsonProperty("status")
    private String status = "New";

    @Column(name = "theme", nullable = false)
    @JsonProperty("theme")
    private String theme;

    @Column(name = "date")
    @JsonProperty("date")
    private LocalDateTime localDateTime = LocalDateTime.now();

    @JsonIgnore
    @JsonProperty("comments")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task_id")
    private Set<CommentTask> commentTasks;





}
