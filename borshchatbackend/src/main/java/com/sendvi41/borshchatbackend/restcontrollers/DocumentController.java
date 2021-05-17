package com.sendvi41.borshchatbackend.restcontrollers;


import com.sendvi41.borshchatbackend.entities.Document;;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;
import com.sendvi41.borshchatbackend.services.DocumentServiceInterface;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.log4j.Logger;
import java.util.Base64;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentServiceInterface documentService;

    private final Logger logger = Logger.getLogger(DocumentController.class.getName());

    @GetMapping(value = "/download")
    public void getDoc(@Param("id") Long id, HttpServletResponse response) throws ServiceResourceNotFoundException, IOException, DecoderException {
        Optional<Document> result = documentService.findById(id);
        if(!result.isPresent())
        {
            throw new ServiceResourceNotFoundException("Not found document");
        }

        Document document = result.get();
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + document.getName();

        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();

        outputStream.write(document.getContent());
        outputStream.close();
    }






    @GetMapping(value = "/getalldocs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Document>> getAllDocs() {
        try {
            List<Document> docs = documentService.getDocuments();

            return new ResponseEntity<>(docs, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/uploaddoc")
    public ResponseEntity<?> uploadDocument(@RequestParam("document") MultipartFile multipartFile) {
        try {
            Document doc = new Document();
            doc.setName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
            doc.setContent(multipartFile.getBytes());
            doc.setSize(multipartFile.getSize());

            documentService.saveDocument(doc);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping(value = "/deletedoc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDocument(@PathVariable("id") long id) {
        try {
            documentService.deleteDocument(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/updatedoc", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDocument(@RequestBody Document doc) {

        try {
            documentService.updateDocument(doc);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
