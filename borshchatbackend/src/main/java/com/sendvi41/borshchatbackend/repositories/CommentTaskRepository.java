package com.sendvi41.borshchatbackend.repositories;


import com.sendvi41.borshchatbackend.entities.CommentTask;
import com.sendvi41.borshchatbackend.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentTaskRepository extends JpaRepository<CommentTask, Long> {
    @Query("select t from CommentTask t where t.task_id.id = :id")
    List<CommentTask> getCommentsbyTaskId(@Param("id") Long id);
}
