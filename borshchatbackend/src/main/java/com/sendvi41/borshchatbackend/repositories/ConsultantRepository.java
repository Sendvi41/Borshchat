package com.sendvi41.borshchatbackend.repositories;

import com.sendvi41.borshchatbackend.entities.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {


}
