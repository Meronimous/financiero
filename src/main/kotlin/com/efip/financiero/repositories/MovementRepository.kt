package com.efip.financiero.repositories;

import com.efip.financiero.entities.Movement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface MovementRepository : JpaRepository<Movement, Long>, JpaSpecificationExecutor<Movement> {
}