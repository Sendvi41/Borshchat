package com.sendvi41.borshchatbackend.services;

import com.sendvi41.borshchatbackend.entities.Document;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;

import java.util.List;

public interface DocumentServiceInterface {


    List<Document> getDocuments();

    void saveDocument(Document doc);

    void deleteDocument(Long id) throws ServiceResourceNotFoundException;

    void updateDocument(Document doc) throws ServiceResourceNotFoundException;
}
