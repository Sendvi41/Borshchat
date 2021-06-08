package com.sendvi41.borshchatbackend.entities;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendvi41.borshchatbackend.dto.TemplateDto;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "templates")
@Data
@JsonAutoDetect
public class Template {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence_Temp")
    @SequenceGenerator(name = "id_Sequence_Temp", sequenceName = "ID_SEQ_TEMP")
    @JsonProperty("id")
    private Long id;

    @Column(name = "message", nullable = false)
    @JsonProperty("message")
    private String message;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultant_id", nullable = false)
    @JsonIgnore
    private Consultant consultant_id;


}
