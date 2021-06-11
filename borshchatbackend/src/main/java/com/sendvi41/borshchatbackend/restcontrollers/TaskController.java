package com.sendvi41.borshchatbackend.restcontrollers;


import com.sendvi41.borshchatbackend.dto.TaskDto;
import com.sendvi41.borshchatbackend.dto.TemplateDto;
import com.sendvi41.borshchatbackend.entities.TaskClient;
import com.sendvi41.borshchatbackend.entities.Template;
import com.sendvi41.borshchatbackend.mappers.TaskMapper;
import com.sendvi41.borshchatbackend.services.TaskClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @PostMapping(value = "/updatetask", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTask(@RequestBody TaskClient task) {

        try {
            taskService.updateTask(task);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }







    @GetMapping(value = "/getalltasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskClient>> getAllTasks() {
        try {
            List<TaskClient> tasks = taskService.getTasks();

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/gettask/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskClient> getTask(@PathVariable("id") long id) {

        try {
            TaskClient task = taskService.getTaskById(id);
            return new ResponseEntity<TaskClient>(task, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<TaskClient>(HttpStatus.NOT_FOUND);
        }
    }


}
