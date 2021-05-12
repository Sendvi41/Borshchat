package com.sendvi41.borshdesk.services;


import com.sendvi41.borshdesk.dto.Task;
import com.sendvi41.borshdesk.dto.Template;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TaskService implements TaskServiceInterface{

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String WS_URI = "http://localhost:8080/task";
    private final Logger logger = Logger.getLogger(TaskService.class.getName());


    @Override
    public Boolean createTask(String nameclient, String surnameclient, String patronymicclient,
                              String email, String comment, Long consultid, String theme, String tracker, String priority) {

        Task task = new Task();
        task.setNameclient(nameclient);
        task.setSurnameclient(surnameclient);
        task.setPatronymicclient(patronymicclient);
        task.setEmail(email);
        task.setComment(comment);
        task.setConsultant_id(consultid);
        task.setTheme(theme);
        task.setTracker(tracker);
        task.setPriority(priority);

        try {
            ResponseEntity<String> response
                    = restTemplate.postForEntity(WS_URI + "/createtask",
                    new HttpEntity<>(task), String.class);
            logger.info("Task has send successfully");
            return response.getStatusCode().equals(HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.warn(ex + task.toString());
            return false;
        }

    }
}
