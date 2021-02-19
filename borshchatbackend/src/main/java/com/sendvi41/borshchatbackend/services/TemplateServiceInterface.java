package com.sendvi41.borshchatbackend.services;

import com.sendvi41.borshchatbackend.entities.Template;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;

import java.util.List;

public interface TemplateServiceInterface {

    public List<Template> getTemplatesbyConsultId(Long id) throws ServiceResourceNotFoundException;
}
