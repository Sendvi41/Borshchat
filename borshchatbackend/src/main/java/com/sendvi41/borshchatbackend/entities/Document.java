package com.sendvi41.borshchatbackend.entities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Data
@JsonAutoDetect
public class Document {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_doc")
    @SequenceGenerator(name = "id_doc", sequenceName = "ID_SEQ_DOC")
    @JsonProperty("id")
    private Long id;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name = "size")
    @JsonProperty("size")
    private long size;

    @Column(name = "date")
    @JsonProperty("date")
    private LocalDateTime localDateTime = LocalDateTime.now();

    @Column(name = "content")
    @Lob
//    @Type(type = "org.hibernate.type.TextType")
    @JsonProperty("content")
    private byte[] content;


}
