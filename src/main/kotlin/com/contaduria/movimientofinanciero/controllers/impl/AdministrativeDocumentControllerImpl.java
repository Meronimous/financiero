package com.contaduria.movimientofinanciero.controllers.impl;

import com.contaduria.movimientofinanciero.controllers.AdministrativeDocumentController;
import com.contaduria.movimientofinanciero.dto.AdministrativeDocumentDto;
import com.contaduria.movimientofinanciero.services.AdministrativeDocumentService;
import com.contaduria.movimientofinanciero.specifications.AdministrativeDocumentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
@RestController
@RequestMapping("/administrative-documents")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
@ControllerAdvice()
public class AdministrativeDocumentControllerImpl implements AdministrativeDocumentController {

    @Autowired
    private AdministrativeDocumentService administrativeDocumentService;

    @Override
    @PostMapping
    public ResponseEntity<AdministrativeDocumentDto> create(AdministrativeDocumentDto administrativeDocumentDto) throws Exception {
        return new ResponseEntity<AdministrativeDocumentDto>(this.administrativeDocumentService.create(administrativeDocumentDto), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<AdministrativeDocumentDto> findById(Long id) throws Exception {
        return new ResponseEntity<AdministrativeDocumentDto>(this.administrativeDocumentService.findById(id), HttpStatus.OK);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<AdministrativeDocumentDto> edit(Long id, AdministrativeDocumentDto administrativeDocumentDto) throws Exception {
        return new ResponseEntity<AdministrativeDocumentDto>(this.administrativeDocumentService.edit(id, administrativeDocumentDto), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<AdministrativeDocumentDto> deleteById(Long id) throws Exception {
        this.administrativeDocumentService.delete(id);
        return new ResponseEntity<AdministrativeDocumentDto>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping
    public HashMap<String, Object> findAll(AdministrativeDocumentSpecification spec, Pageable pageable) {
        return this.administrativeDocumentService.findAll(spec, pageable);
    }
}
