package com.sendvi41.borshchatbackend.entities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "consultants")
@Data
@JsonAutoDetect
public class Consultant{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ_PROD")
    @JsonProperty("id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @JsonProperty("name")
    private String name;

    @Column(name = "password", nullable = false)
    @JsonProperty("password")
    private String password;
}