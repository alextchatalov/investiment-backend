package com.monitor.repository;

import com.monitor.domain.WalletRebalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRebalanceRepository extends JpaRepository<WalletRebalance, Long> {

}
