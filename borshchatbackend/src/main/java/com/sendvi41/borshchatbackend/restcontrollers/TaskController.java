package com.sendvi41.borshchatbackend.restcontrollers;


import com.sendvi41.borshchatbackend.dto.TaskDto;
import com.sendvi41.borshchatbackend.entities.TaskClient;
import com.sendvi41.borshchatbackend.mappers.TaskMapper;
import com.sendvi41.borshchatbackend.services.TaskClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {


    @Autowired
    TaskClientInterface taskService;

    @Autowired
    TaskMapper taskMapper;


    @PostMapping(value = "/createtask", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {

        try {
            TaskClient task = taskMapper.convertToEntity(taskDto);
            taskService.saveTask(task);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


}
