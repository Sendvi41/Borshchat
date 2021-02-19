package com.sendvi41.borshchatbackend.services;


import com.sendvi41.borshchatbackend.entities.Template;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;
import com.sendvi41.borshchatbackend.repositories.ConsultantRepository;
import com.sendvi41.borshchatbackend.repositories.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    @Autowired
    TemplateRepository templateRepository;

    public List<Template> getTemplatesbyConsultId(Long id) throws ServiceResourceNotFoundException{

        if (templateRepository.getTemplatesbyConsultId(id) != null) {
            return templateRepository.getTemplatesbyConsultId(id);
        }else{
            throw new ServiceResourceNotFoundException("Don't find templates by consultant id" +
                    ""+id);
        }

    }

}
