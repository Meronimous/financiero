package com.contaduria.movimientofinanciero.repositories;

import com.contaduria.movimientofinanciero.entities.AdministrativeDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AdministrativeDocumentRepository extends JpaRepository<AdministrativeDocument, Long>, JpaSpecificationExecutor<AdministrativeDocument> {

    Page<AdministrativeDocument> findAll(Specification<AdministrativeDocument> spec, Pageable pageable);
    public Optional<AdministrativeDocument> findByYearAndCodeOrganismAndNumber(Integer year, Integer codeOrganism, Integer number);

}
