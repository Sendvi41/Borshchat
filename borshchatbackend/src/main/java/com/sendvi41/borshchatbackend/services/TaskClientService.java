package com.sendvi41.borshchatbackend.services;

import com.sendvi41.borshchatbackend.entities.TaskClient;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;
import com.sendvi41.borshchatbackend.repositories.TaskClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskClientService implements TaskClientInterface {

    @Autowired
    TaskClientRepository taskClientRepository;


    @Override
    @Transactional
    public List<TaskClient> getTasks() {
            return taskClientRepository.findAll();
    }

    @Override
    @Transactional
    public void saveTask(TaskClient task) {
        taskClientRepository.save(task);
    }

    @Override
    @Transactional
    public TaskClient getTaskById(Long id) throws ServiceResourceNotFoundException  {
        Optional <TaskClient> task = taskClientRepository.findById(id);
        if(task.isEmpty())
        {
            throw new ServiceResourceNotFoundException("No such id " + id);
        }
         return task.get();
    }

    @Override
    @Transactional
    public void deleteTask(Long id) throws ServiceResourceNotFoundException {
        if (taskClientRepository.findById(id).isPresent()) {
            taskClientRepository.deleteById(id);

        } else {
            throw new ServiceResourceNotFoundException("No such id " + id);
        }

    }

    @Override
    @Transactional
    public void updateTask(TaskClient task) throws ServiceResourceNotFoundException {
        if (task.getId() != 0) {
            taskClientRepository.save(task);
        } else {
            throw new ServiceResourceNotFoundException("Not found task");

        }

    }
}
