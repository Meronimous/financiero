package com.contaduria.movimientofinanciero.repositories;

import com.contaduria.movimientofinanciero.entities.FinancialUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<FinancialUser, Long>, JpaSpecificationExecutor<FinancialUser> {

    Page<FinancialUser> findAll(Specification<FinancialUser> spec, Pageable pageable);


}
