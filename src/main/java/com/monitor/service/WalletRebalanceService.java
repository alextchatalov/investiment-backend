package com.monitor.service;

import com.monitor.business.WalletRebalanceBusiness;
import com.monitor.domain.Investiment;
import com.monitor.domain.WalletRebalance;
import com.monitor.dto.WalletRebalanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class WalletRebalanceService {

    @Autowired
    private WalletRebalanceBusiness business;

    public List<WalletRebalanceDTO> getAll() throws ExecutionException, InterruptedException {
        return  business.getAll();
    }

    public void save(WalletRebalance rebalance) {
        business.save(rebalance);
    }

    public BigDecimal getPrice(Investiment investiment) throws IOException {
        return business.getPrice(investiment);
    }
}
