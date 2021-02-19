package com.sendvi41.borshdesk.services;

import com.sendvi41.borshdesk.dto.Template;

import java.util.List;

public interface TemplateServiceInterface {

    List<Template> getTemplates(Long id);

    Boolean createTemplate(String message, Long consultid);

    Boolean deleteTemplate(Long id);
}
