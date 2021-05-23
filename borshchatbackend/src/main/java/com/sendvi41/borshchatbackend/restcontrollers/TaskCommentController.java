package com.sendvi41.borshchatbackend.restcontrollers;

import com.sendvi41.borshchatbackend.dto.CommentTaskDto;
import com.sendvi41.borshchatbackend.dto.TaskDto;
import com.sendvi41.borshchatbackend.dto.TemplateDto;
import com.sendvi41.borshchatbackend.entities.CommentTask;
import com.sendvi41.borshchatbackend.entities.TaskClient;


import com.sendvi41.borshchatbackend.entities.Template;
import com.sendvi41.borshchatbackend.mappers.CommentTaskMapper;
import com.sendvi41.borshchatbackend.services.CommentTaskServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


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


    @GetMapping(value = "/getcomments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentTaskDto>> getComments(@PathVariable("id") long taskid) {

        try {
            List<CommentTask> comments = commentTaskService.getCommentsbyTaskId(taskid);
            List<CommentTaskDto> commentsTaskDto = comments.stream()
                    .map(commenTaskMapper::convertToDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<List<CommentTaskDto>>(commentsTaskDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<CommentTaskDto>>(HttpStatus.NOT_FOUND);
        }
    }



}
