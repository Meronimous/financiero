package com.contaduria.movimientofinanciero.services;

import com.contaduria.movimientofinanciero.dto.AdministrativeDocumentDto;
import com.contaduria.movimientofinanciero.specifications.AdministrativeDocumentSpecification;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

public interface AdministrativeDocumentService {
    public AdministrativeDocumentDto create(AdministrativeDocumentDto administrativeDocumentDto) throws Exception;

    public AdministrativeDocumentDto edit(Long id, AdministrativeDocumentDto administrativeDocumentDto);

    public void delete(Long id);

    public AdministrativeDocumentDto findById(Long id);

    public HashMap<String, Object> findAll(AdministrativeDocumentSpecification spec, Pageable pageable);
}
