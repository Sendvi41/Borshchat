package com.sendvi41.borshchatbackend.services;

import com.sendvi41.borshchatbackend.entities.Document;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;
import com.sendvi41.borshchatbackend.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService implements DocumentServiceInterface {



    @Autowired
    DocumentRepository documentRepository;

    @Override
    @Transactional
    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    @Override
    @Transactional
    public void saveDocument(Document doc) {
        documentRepository.save(doc);
    }

    @Override
    @Transactional
    public void deleteDocument(Long id) throws ServiceResourceNotFoundException {
        if (documentRepository.findById(id).isPresent()) {
            documentRepository.deleteById(id);

        } else {
            throw new ServiceResourceNotFoundException("No such id " + id);
        }
    }

    @Override
    @Transactional
    public void updateDocument(Document doc) throws ServiceResourceNotFoundException {
        if (doc.getId() != 0) {
            documentRepository.save(doc);
        } else {
            throw new ServiceResourceNotFoundException("Not found document");
        }
    }
}
