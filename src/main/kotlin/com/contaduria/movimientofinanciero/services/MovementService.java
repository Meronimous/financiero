package com.contaduria.movimientofinanciero.services;

import com.contaduria.movimientofinanciero.dto.MovementDto;
import com.contaduria.movimientofinanciero.specifications.MovementSpecification;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface MovementService {
    public MovementDto create(MovementDto movementDto) throws Exception;

    public MovementDto edit(Long id, MovementDto movementDto);

    public void delete(Long id) throws Exception;

    public MovementDto findById(Long id);

    public HashMap<String, Object> findAll(MovementSpecification spec, Pageable pageable);
}
