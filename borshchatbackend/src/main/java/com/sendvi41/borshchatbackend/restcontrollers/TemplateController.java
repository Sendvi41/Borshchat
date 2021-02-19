package com.sendvi41.borshchatbackend.restcontrollers;


import com.sendvi41.borshchatbackend.dto.TemplateDto;
import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.entities.Template;
import com.sendvi41.borshchatbackend.mappers.TemplateMapper;
import com.sendvi41.borshchatbackend.services.TemplateService;
import com.sendvi41.borshchatbackend.services.TemplateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    TemplateServiceInterface templateService;

    @Autowired
    TemplateMapper templateMapper;

    @GetMapping(value = "/gettemplates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TemplateDto>> getTemplates(@PathVariable("id") long consultId) {

        try {
            List<Template> templates = templateService.getTemplatesbyConsultId(consultId);
            List<TemplateDto> templatesDto = templates.stream()
                    .map(templateMapper::convertToDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<List<TemplateDto>>(templatesDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<TemplateDto>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletetemplate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTemplate(@PathVariable("id") long id) {
        try {
            templateService.deleteTemplate(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping(value = "/createtemplate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTemplate(@RequestBody TemplateDto templateDto) {

        try {
            Template template = templateMapper.convertToEntity(templateDto);
            templateService.saveTemplate(template);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/updatetemplate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTemplate(@RequestBody TemplateDto templateDto) {

        try {
            Template template = templateMapper.convertToEntity(templateDto);
            templateService.updateTemplate(template);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
