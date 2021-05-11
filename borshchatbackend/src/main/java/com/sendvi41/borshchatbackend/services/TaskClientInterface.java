package com.sendvi41.borshchatbackend.services;

import com.sendvi41.borshchatbackend.entities.TaskClient;
import com.sendvi41.borshchatbackend.entities.Template;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;

import java.util.List;

public interface TaskClientInterface  {

    List<TaskClient> getTasks();

    void saveTask(TaskClient task);

    void deleteTask(Long id) throws ServiceResourceNotFoundException;

    void updateTask(TaskClient task) throws ServiceResourceNotFoundException;


}
