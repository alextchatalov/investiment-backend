package com.monitor.service;

import com.monitor.business.WalletRebalanceBusiness;
import com.monitor.domain.WalletRebalance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WalletRebalanceService {

    @Autowired
    private WalletRebalanceBusiness business;

    public List<WalletRebalance> getAll() {
        return  business.getAll();
    }
}
