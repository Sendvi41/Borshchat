package com.sendvi41.borshdesk.services;

public interface TaskServiceInterface {

    Boolean createTask(String nameclient, String surnameclient, String patronymicclient,
                       String email, String comment, Long consultid);
}
