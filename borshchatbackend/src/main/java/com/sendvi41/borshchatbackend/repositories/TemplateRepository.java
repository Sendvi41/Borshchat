package com.sendvi41.borshchatbackend.repositories;

import com.sendvi41.borshchatbackend.dto.TemplateDto;
import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    @Query("select t from Template t where t.consultant_id.id = :id")
    List<Template> getTemplatesbyConsultId(@Param("id") Long id);


}
