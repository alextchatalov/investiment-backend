package com.monitor.repository;

import com.monitor.domain.Investiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestimentRepository extends JpaRepository<Investiment, String> {
    Investiment findInvestmentByInvestimentCode(String code);
}
