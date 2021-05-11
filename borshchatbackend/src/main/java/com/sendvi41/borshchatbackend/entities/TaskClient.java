package com.sendvi41.borshchatbackend.entities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Data
@JsonAutoDetect
public class TaskClient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ_PROD")
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




}
