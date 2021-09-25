package com.contaduria.movimientofinanciero.repositories;

import com.contaduria.movimientofinanciero.entities.FundRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FundRequestRepository extends JpaRepository<FundRequest, Long>, JpaSpecificationExecutor<FundRequest> {

    Page<FundRequest> findAll(Specification<FundRequest> spec, Pageable pageable);

}
