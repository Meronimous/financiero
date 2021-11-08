package com.contaduria.movimientofinanciero.services.impl;

import com.contaduria.movimientofinanciero.dto.AdministrativeDocumentDto;
import com.contaduria.movimientofinanciero.entities.AdministrativeDocument;
import com.contaduria.movimientofinanciero.exceptions.DataIntegrityViolationException;
import com.contaduria.movimientofinanciero.repositories.AdministrativeDocumentRepository;
import com.contaduria.movimientofinanciero.services.AdministrativeDocumentService;
import com.contaduria.movimientofinanciero.services.ConvertService;
import com.contaduria.movimientofinanciero.specifications.AdministrativeDocumentSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AdministrativeDocumentServiceImpl implements AdministrativeDocumentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdministrativeDocumentRepository administrativeDocumentRepository;


    @Autowired
    private ConvertService convertService;

    @Override
    public AdministrativeDocumentDto create(AdministrativeDocumentDto administrativeDocumentDto) throws Exception {
        this.logger.debug("START create(" + administrativeDocumentDto + ")");
        Specification<AdministrativeDocument> specific = Specification
                .where(AdministrativeDocumentSpecification.documentnumber(administrativeDocumentDto.getNumber())
                        .and(AdministrativeDocumentSpecification.documentCodeOrganism(administrativeDocumentDto.getCodeOrganism()))
                        .and(AdministrativeDocumentSpecification.documentYear(administrativeDocumentDto.getYear())));
        List<AdministrativeDocument> administrativeDocuments = administrativeDocumentRepository.findAll(specific);
        if (administrativeDocuments.isEmpty()) {
        return this.convertService.convertToDto(this.administrativeDocumentRepository.save(this.convertService.convertToEntity(administrativeDocumentDto)));}
        else {
            throw new DataIntegrityViolationException("Ya existe un expediente con el denominador " + administrativeDocumentDto.getCodeOrganism() + "-" + administrativeDocumentDto.getNumber() + "/" + administrativeDocumentDto.getYear());
        }
    }


    @Override
    public AdministrativeDocumentDto edit(Long id, AdministrativeDocumentDto administrativeDocumentDto) {
        this.logger.debug("START edit(" + id + ", " + administrativeDocumentDto + ")");
        this.administrativeDocumentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe una orden de entrega con ID=" + id + "."));
        administrativeDocumentDto.setId(id);
        return this.convertService.convertToDto(this.administrativeDocumentRepository.save(this.convertService.convertToEntity(administrativeDocumentDto)));
    }

    @Override
    public void delete(Long id) {
        this.logger.debug("START delete(" + id + ")");
        AdministrativeDocument administrativeDocument = this.administrativeDocumentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe una orden de entrega con ID=" + id + "."));
        this.administrativeDocumentRepository.deleteById(id);
    }

    @Override
    public AdministrativeDocumentDto findById(Long id) {
        this.logger.debug("START findById(" + id + ")");
        return this.convertService.convertToDto(this.administrativeDocumentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe una orden de entrega con ID=" + id + ".")));
    }

    @Override
    public HashMap<String, Object> findAll(AdministrativeDocumentSpecification spec, Pageable pageable) {
        return this.convertService.convertToFormatAdministrativeDocument(this.administrativeDocumentRepository.findAll(spec, pageable));
    }

    @Override
    public AdministrativeDocument findOrCreate(AdministrativeDocument administrativeDocument) {
        this.logger.debug("START findOrCreate({},{},{}",administrativeDocument.getYear(),administrativeDocument.getCodeOrganism(),administrativeDocument.getNumber());
        AdministrativeDocument finalAdministrativeDocument = administrativeDocument;
        return this.administrativeDocumentRepository.findByYearAndCodeOrganismAndNumber(administrativeDocument.getYear(),
                administrativeDocument.getCodeOrganism(), administrativeDocument.getNumber()).orElseGet(() -> this.administrativeDocumentRepository.save(finalAdministrativeDocument));
    }
}
