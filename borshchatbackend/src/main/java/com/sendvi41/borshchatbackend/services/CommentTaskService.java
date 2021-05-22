package com.sendvi41.borshchatbackend.services;


import com.sendvi41.borshchatbackend.entities.CommentTask;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;

import com.sendvi41.borshchatbackend.repositories.CommentTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CommentTaskService implements CommentTaskServiceInterface {

    @Autowired
    CommentTaskRepository commentTaskRepository;

    @Override
    @Transactional
    public void addCommentTask(CommentTask commentTask) throws ServiceResourceNotFoundException {

        if (commentTask.getTask_id().getId() != 0) {
            commentTaskRepository.save(commentTask);
        } else {
            throw new ServiceResourceNotFoundException("Not found task for comment");

        }
    }
}
