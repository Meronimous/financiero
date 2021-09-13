package com.contaduria.movimientofinanciero.repositories

import com.contaduria.movimientofinanciero.entities.AdministrativeDocument
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin


@CrossOrigin(origins = ["*"])
@RepositoryRestResource(path = "administrative-documents")
interface AdministrativeDocumentRepository : PagingAndSortingRepository<AdministrativeDocument?, Long?>