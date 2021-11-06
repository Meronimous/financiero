package com.contaduria.movimientofinanciero.services.impl;

import com.contaduria.movimientofinanciero.dto.FundRequestDto;
import com.contaduria.movimientofinanciero.entities.FundRequest;
import com.contaduria.movimientofinanciero.exceptions.ResourceNotFoundException;
import com.contaduria.movimientofinanciero.repositories.FundRequestRepository;
import com.contaduria.movimientofinanciero.services.FundRequestService;
import com.contaduria.movimientofinanciero.services.ConvertService;
import com.contaduria.movimientofinanciero.specifications.FundRequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FundRequestServiceImpl implements FundRequestService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FundRequestRepository fundRequestRepository;


    @Autowired
    private ConvertService convertService;

    @Override
    public FundRequestDto create(FundRequestDto fundRequestDto) {
        this.logger.debug("START create(" + fundRequestDto + ")");
        fundRequestDto.setId(0L);
        return this.convertService.convertToDto(this.fundRequestRepository.save(this.convertService.convertToEntity(fundRequestDto)));
    }

    @Override
    public FundRequestDto edit(Long id, FundRequestDto fundRequestDto) {
        this.logger.debug("START edit(" + id + ", " + fundRequestDto + ")");
        this.fundRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe una orden de entrega con ID=" + id + "."));
        fundRequestDto.setId(id);
        return this.convertService.convertToDto(this.fundRequestRepository.save(this.convertService.convertToEntity(fundRequestDto)));
    }

    @Override
    public void delete(Long id) {
        this.logger.debug("START delete(" + id + ")");
        FundRequest fundRequest = this.fundRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe una orden de entrega con ID=" + id + "."));
        this.fundRequestRepository.deleteById(id);
    }

    @Override
    public FundRequestDto findById(Long id) {
        this.logger.debug("START findById(" + id + ")");
        return this.convertService.convertToDto(this.fundRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe una orden de entrega con ID=" + id + ".")));
    }

    @Override
    public HashMap<String, Object> findAll(FundRequestSpecification spec, Pageable pageable) {
        return this.convertService.convertToFormatFundRequest(this.fundRequestRepository.findAll(spec, pageable));
    }

}
