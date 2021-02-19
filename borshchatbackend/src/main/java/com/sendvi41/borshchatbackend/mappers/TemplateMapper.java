package com.sendvi41.borshchatbackend.mappers;


import com.sendvi41.borshchatbackend.dto.TemplateDto;
import com.sendvi41.borshchatbackend.entities.Template;
import com.sendvi41.borshchatbackend.repositories.ConsultantRepository;
import com.sendvi41.borshchatbackend.repositories.TemplateRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Objects;

@Component
public class TemplateMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ConsultantRepository consultantRepository;

    public TemplateDto convertToDto(Template temp)  {
        TemplateDto tempDto = modelMapper.map(temp, TemplateDto.class);
        return tempDto;
    }

    public Template convertToEntity(TemplateDto TemplateDto) throws ParseException {
        Template template = modelMapper.map( TemplateDto, Template.class);

        return template;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Template.class, TemplateDto.class)
                .addMappings(m -> m.skip(TemplateDto::setConsultant_id)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(TemplateDto.class, Template.class)
                .addMappings(m -> m.skip(Template::setConsultant_id)).setPostConverter(toEntityConverter());
    }



    public Converter<TemplateDto, Template> toEntityConverter() {
        return context -> {
            TemplateDto source = context.getSource();
            Template destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<Template, TemplateDto> toDtoConverter() {
        return context -> {
            Template source = context.getSource();
            TemplateDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }
    public void mapSpecificFields(Template source, TemplateDto destination) {
        destination.setConsultant_id(Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getConsultant_id().getId());
    }

    void mapSpecificFields(TemplateDto source, Template destination) {
        destination.setConsultant_id(consultantRepository.findById(source.getConsultant_id()).orElse(null));
    }


}
