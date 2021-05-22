package com.sendvi41.borshchatbackend.mappers;




import com.sendvi41.borshchatbackend.dto.CommentTaskDto;
import com.sendvi41.borshchatbackend.entities.CommentTask;
import com.sendvi41.borshchatbackend.repositories.TaskClientRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Objects;

public class CommentTaskMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    TaskClientRepository taskClientRepository;

    public CommentTaskDto convertToDto(CommentTask commentTask)  {
        CommentTaskDto commentTaskDto = modelMapper.map(commentTask, CommentTaskDto.class);
        return commentTaskDto;
    }

    public CommentTask convertToEntity(CommentTaskDto commentTaskDto) throws ParseException {
        CommentTask commentTask = modelMapper.map( commentTaskDto, CommentTask.class);
        return commentTask;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(CommentTask.class, CommentTaskDto.class)
                .addMappings(m -> m.skip(CommentTaskDto::setTask_id)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(CommentTaskDto.class, CommentTask.class)
                .addMappings(m -> m.skip(CommentTask::setTask_id)).setPostConverter(toEntityConverter());
    }



    public Converter<CommentTaskDto, CommentTask> toEntityConverter() {
        return context -> {
            CommentTaskDto source = context.getSource();
            CommentTask destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<CommentTask, CommentTaskDto> toDtoConverter() {
        return context -> {
            CommentTask source = context.getSource();
            CommentTaskDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }
    public void mapSpecificFields(CommentTask source, CommentTaskDto destination) {
        destination.setTask_id(Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getTask_id().getId());
    }

    void mapSpecificFields(CommentTaskDto source, CommentTask destination) {
        destination.setTask_id(taskClientRepository.findById(source.getTask_id()).orElse(null));
    }

}
