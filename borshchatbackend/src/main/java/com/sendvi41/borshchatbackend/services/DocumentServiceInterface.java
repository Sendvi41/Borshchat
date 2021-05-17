package com.sendvi41.borshchatbackend.services;

import com.sendvi41.borshchatbackend.entities.Document;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DocumentServiceInterface {


    List<Document> getDocuments();

    void saveDocument(Document doc);

    Optional<Document> findById(Long id);

    void deleteDocument(Long id) throws ServiceResourceNotFoundException;

    void updateDocument(Document doc) throws ServiceResourceNotFoundException;
}
