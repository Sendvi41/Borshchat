package com.sendvi41.borshchatbackend.restcontrollers;

import com.sendvi41.borshchatbackend.dto.CommentTaskDto;
import com.sendvi41.borshchatbackend.dto.TaskDto;
import com.sendvi41.borshchatbackend.entities.CommentTask;
import com.sendvi41.borshchatbackend.entities.TaskClient;


import com.sendvi41.borshchatbackend.mappers.CommentTaskMapper;
import com.sendvi41.borshchatbackend.services.CommentTaskServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/taskcomment")
public class TaskCommentController {


    @Autowired
    CommentTaskServiceInterface  commentTaskService;

    @Autowired
    CommentTaskMapper commenTaskMapper;

    private final Logger logger = Logger.getLogger(TaskCommentController.class.getName());


    @PostMapping(value = "/addcomment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody CommentTaskDto commentTaskDto) {
        try {
            CommentTask commentTask = commenTaskMapper.convertToEntity(commentTaskDto);
            commentTaskService.addCommentTask(commentTask);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
