package com.sendvi41.borshchatbackend.repositories;

import com.sendvi41.borshchatbackend.entities.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
    @Query("select c from Consultant c where c.name = :name and c.password = :password")
    Consultant validate(@Param("name") String name, @Param("password") String password);

}
