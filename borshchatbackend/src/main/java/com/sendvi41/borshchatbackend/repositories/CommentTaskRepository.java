package com.sendvi41.borshchatbackend.repositories;


import com.sendvi41.borshchatbackend.entities.CommentTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentTaskRepository extends JpaRepository<CommentTask, Long> {

}
