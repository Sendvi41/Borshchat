package com.sendvi41.borshchatbackend.repositories;

import com.sendvi41.borshchatbackend.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>  {
}
