package com.sendvi41.borshchatbackend.services;

import com.sendvi41.borshchatbackend.entities.Template;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;

import java.util.List;

public interface TemplateServiceInterface {

    List<Template> getTemplatesbyConsultId(Long id) throws ServiceResourceNotFoundException;

    void saveTemplate(Template template);

    void deleteTemplate(Long id) throws ServiceResourceNotFoundException;

    void updateTemplate(Template template) throws ServiceResourceNotFoundException;

}
