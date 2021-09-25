package com.contaduria.movimientofinanciero.controllers.impl;

import com.contaduria.movimientofinanciero.controllers.MovementController;
import com.contaduria.movimientofinanciero.dto.MovementDto;
import com.contaduria.movimientofinanciero.services.MovementService;
import com.contaduria.movimientofinanciero.specifications.MovementSpecification;
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
@RequestMapping("/movements")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
@ControllerAdvice()
public class MovementControllerImpl implements MovementController {

    @Autowired
    private MovementService movementService;

    @Override
    @PostMapping
    public ResponseEntity<MovementDto> create(MovementDto movementDto) throws Exception {
        return new ResponseEntity<MovementDto>(this.movementService.create(movementDto), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<MovementDto> findById(Long id) throws Exception {
        return new ResponseEntity<MovementDto>(this.movementService.findById(id), HttpStatus.OK);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<MovementDto> edit(Long id, MovementDto movementDto) throws Exception {
        return new ResponseEntity<MovementDto>(this.movementService.edit(id, movementDto), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<MovementDto> deleteById(Long id) throws Exception {
        this.movementService.delete(id);
        return new ResponseEntity<MovementDto>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping
    public HashMap<String, Object> findAll(MovementSpecification spec, Pageable pageable) {
        return this.movementService.findAll(spec, pageable);
    }
}
