package com.monitor.repository;

import com.monitor.domain.Investiment;
import org.springframework.data.repository.CrudRepository;

public interface InvestimentRepository extends CrudRepository<Investiment, String> {
    Investiment findInvestmentByInvestimentCode(String code);
}
