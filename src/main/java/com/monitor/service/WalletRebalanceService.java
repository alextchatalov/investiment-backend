package com.monitor.service;

import com.monitor.business.WalletRebalanceBusiness;
import com.monitor.domain.WalletRebalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletRebalanceService {

    @Autowired
    private WalletRebalanceBusiness business;

    public List<WalletRebalance> getAll() {
        return  business.getAll();
    }

    public void save(WalletRebalance rebalance) {
        business.save(rebalance);
    }
}
