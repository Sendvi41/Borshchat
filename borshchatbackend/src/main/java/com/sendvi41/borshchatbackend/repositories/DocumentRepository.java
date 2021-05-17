package com.sendvi41.borshchatbackend.repositories;

import com.sendvi41.borshchatbackend.entities.Document;
import com.sendvi41.borshchatbackend.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>  {

//    @Query("select t.id, t.name, encode(t.content,'hex') from Document as t where t.id = :id")
//    Document getOneDocumentById(@Param("id") Long id);
}
