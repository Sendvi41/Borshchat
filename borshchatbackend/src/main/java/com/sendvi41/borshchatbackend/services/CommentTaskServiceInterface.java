package com.sendvi41.borshchatbackend.services;

import com.sendvi41.borshchatbackend.entities.CommentTask;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;

public interface CommentTaskServiceInterface {


    void addCommentTask(CommentTask commentTask) throws ServiceResourceNotFoundException;
}
