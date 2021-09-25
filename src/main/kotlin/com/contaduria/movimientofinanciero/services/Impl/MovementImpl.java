package com.contaduria.movimientofinanciero.services.impl;

import com.contaduria.movimientofinanciero.dto.MovementDto;
import com.contaduria.movimientofinanciero.entities.Movement;
import com.contaduria.movimientofinanciero.repositories.MovementRepository;
import com.contaduria.movimientofinanciero.services.MovementService;
import com.contaduria.movimientofinanciero.services.ConvertService;
import com.contaduria.movimientofinanciero.specifications.MovementSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MovementImpl implements MovementService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MovementRepository movementRepository;


    @Autowired
    private ConvertService convertService;

    @Override
    public MovementDto create(MovementDto movementDto) {
        this.logger.debug("START create(" + movementDto + ")");
        return this.convertService.convertToDto(this.movementRepository.save(this.convertService.convertToEntity(movementDto)));
    }

    @Override
    public MovementDto edit(Long id, MovementDto movementDto) {
        this.logger.debug("START edit(" + id + ", " + movementDto + ")");
        this.movementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe una orden de entrega con ID=" + id + "."));
        movementDto.setId(id);
        return this.convertService.convertToDto(this.movementRepository.save(this.convertService.convertToEntity(movementDto)));
    }

    @Override
    public void delete(Long id) {
        this.logger.debug("START delete(" + id + ")");
        Movement movement = this.movementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe una orden de entrega con ID=" + id + "."));
        this.movementRepository.deleteById(id);
    }

    @Override
    public MovementDto findById(Long id) {
        this.logger.debug("START findById(" + id + ")");
        return this.convertService.convertToDto(this.movementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe una orden de entrega con ID=" + id + ".")));
    }

    @Override
    public HashMap<String, Object> findAll(MovementSpecification spec, Pageable pageable) {
        return this.convertService.convertToFormatMovement(this.movementRepository.findAll(spec, pageable));
    }

}
