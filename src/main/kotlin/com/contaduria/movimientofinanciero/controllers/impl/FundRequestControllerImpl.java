package com.contaduria.movimientofinanciero.controllers.impl;

import com.contaduria.movimientofinanciero.controllers.FundRequestController;
import com.contaduria.movimientofinanciero.dto.FundRequestDto;
import com.contaduria.movimientofinanciero.services.FundRequestService;
import com.contaduria.movimientofinanciero.specifications.FundRequestSpecification;
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
@RequestMapping("/fund-requests")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
@ControllerAdvice()
public class FundRequestControllerImpl implements FundRequestController {

    @Autowired
    private FundRequestService fundRequestService;

    @Override
    @PostMapping
    public ResponseEntity<FundRequestDto> create(FundRequestDto fundRequestDto) throws Exception {
        return new ResponseEntity<FundRequestDto>(this.fundRequestService.create(fundRequestDto), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<FundRequestDto> findById(Long id) throws Exception {
        return new ResponseEntity<FundRequestDto>(this.fundRequestService.findById(id), HttpStatus.OK);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<FundRequestDto> edit(Long id, FundRequestDto fundRequestDto) throws Exception {
        return new ResponseEntity<FundRequestDto>(this.fundRequestService.edit(id, fundRequestDto), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<FundRequestDto> deleteById(Long id) throws Exception {
        this.fundRequestService.delete(id);
        return new ResponseEntity<FundRequestDto>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping
    public HashMap<String, Object> findAll(FundRequestSpecification spec, Pageable pageable) {
        return this.fundRequestService.findAll(spec, pageable);
    }
}
