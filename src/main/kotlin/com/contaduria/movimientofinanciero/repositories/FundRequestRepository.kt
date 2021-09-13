package com.contaduria.movimientofinanciero.repositories

import com.contaduria.movimientofinanciero.entities.FundRequest
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin


@CrossOrigin(origins = ["*"])
@RepositoryRestResource(path = "fundrequests")
interface FundRequestRepository : PagingAndSortingRepository<FundRequest?, Long?>