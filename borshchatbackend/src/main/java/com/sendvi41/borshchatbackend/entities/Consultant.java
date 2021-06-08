package com.sendvi41.borshchatbackend.entities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "consultants")
@Getter
@Setter
@JsonAutoDetect
public class Consultant{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence_consult")
    @SequenceGenerator(name = "id_Sequence_consult", sequenceName = "ID_SEQ_CONSULT")
    @JsonProperty("id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @JsonProperty("name")
    private String name;

    @Column(name = "password", nullable = false)
    @JsonProperty("password")
    private String password;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "consultant_id")
    private Set<Template> templates;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "consultant_id")
    private Set<TaskClient> tasks;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "consultant_id")
    private Set<CommentTask> commentTasks;


}
