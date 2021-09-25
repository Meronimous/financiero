package com.contaduria.movimientofinanciero.repositories;

import com.contaduria.movimientofinanciero.entities.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovementRepository extends JpaRepository<Movement, Long>, JpaSpecificationExecutor<Movement> {

    Page<Movement> findAll(Specification<Movement> spec, Pageable pageable);

}
