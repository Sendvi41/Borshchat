package com.sendvi41.borshchatbackend.mappers;


import com.sendvi41.borshchatbackend.dto.TaskDto;
import com.sendvi41.borshchatbackend.dto.TemplateDto;
import com.sendvi41.borshchatbackend.entities.TaskClient;
import com.sendvi41.borshchatbackend.entities.Template;
import com.sendvi41.borshchatbackend.repositories.ConsultantRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Objects;

@Component
public class TaskMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ConsultantRepository consultantRepository;


    public TaskDto convertToDto(TaskClient task)  {
        TaskDto tempDto = modelMapper.map(task, TaskDto.class);
        return tempDto;
    }

    public TaskClient convertToEntity(TaskDto TaskDto) throws ParseException {
        TaskClient task = modelMapper.map( TaskDto, TaskClient.class);

        return task;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(TaskClient.class, TaskDto.class)
                .addMappings(m -> m.skip(TaskDto::setConsultant_id)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(TaskDto.class, TaskClient.class)
                .addMappings(m -> m.skip(TaskClient::setConsultant_id)).setPostConverter(toEntityConverter());
    }



    public Converter<TaskDto, TaskClient> toEntityConverter() {
        return context -> {
            TaskDto source = context.getSource();
            TaskClient destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<TaskClient, TaskDto> toDtoConverter() {
        return context -> {
            TaskClient source = context.getSource();
            TaskDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }
    public void mapSpecificFields(TaskClient source, TaskDto destination) {
        destination.setConsultant_id(Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getConsultant_id().getId());
    }

    void mapSpecificFields(TaskDto source, TaskClient destination) {
        destination.setConsultant_id(consultantRepository.findById(source.getConsultant_id()).orElse(null));
    }

}
