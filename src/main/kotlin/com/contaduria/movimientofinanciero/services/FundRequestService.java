package com.contaduria.movimientofinanciero.services;

import com.contaduria.movimientofinanciero.dto.FundRequestDto;
import com.contaduria.movimientofinanciero.entities.AdministrativeDocument;
import com.contaduria.movimientofinanciero.entities.FundRequest;
import com.contaduria.movimientofinanciero.specifications.FundRequestSpecification;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

public interface FundRequestService {
    public FundRequestDto create(FundRequestDto fundRequestDto) throws Exception;

    public FundRequestDto edit(Long id, FundRequestDto fundRequestDto);

    public void delete(Long id);

    public FundRequestDto findById(Long id);

    public HashMap<String, Object> findAll(FundRequestSpecification spec, Pageable pageable);

    public FundRequestDto findByNumberYearOrganismCode(FundRequestDto fundRequestDto);
}
