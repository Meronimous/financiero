package com.contaduria.movimientofinanciero.repositories

import com.contaduria.movimientofinanciero.entities.Movement
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin


@CrossOrigin(origins = ["*"])
@RepositoryRestResource(path = "movements")
interface MovementRepository : PagingAndSortingRepository<Movement?, Long?>