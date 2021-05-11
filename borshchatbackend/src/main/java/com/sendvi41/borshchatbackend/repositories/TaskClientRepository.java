package com.sendvi41.borshchatbackend.repositories;

import com.sendvi41.borshchatbackend.entities.TaskClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskClientRepository extends JpaRepository<TaskClient, Long> {
}
