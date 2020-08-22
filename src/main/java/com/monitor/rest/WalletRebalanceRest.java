package com.monitor.rest;

import com.monitor.domain.Investiment;
import com.monitor.domain.WalletRebalance;
import com.monitor.dto.WalletRebalanceDTO;
import com.monitor.service.WalletRebalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/rebalance")
public class WalletRebalanceRest {

    @Autowired
    private WalletRebalanceService service;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<WalletRebalanceDTO> getAll() throws ExecutionException, InterruptedException {
        return service.getAll();
    }

    @PostMapping("/update")
    public void updateRebalance(@RequestBody WalletRebalanceDTO rebalanceDTO) {
        try {
            service.save(castToEntity(rebalanceDTO));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private WalletRebalance castToEntity(WalletRebalanceDTO rebalanceDTO) {
        WalletRebalance rebalance = new WalletRebalance();
        rebalance.setId(rebalanceDTO.getId());
        rebalance.setInvestiment(rebalanceDTO.getInvestiment());
        rebalance.setNote(rebalanceDTO.getNote());
        rebalance.setPercentWallet(rebalanceDTO.getPercentWallet());
        rebalance.setIdealTotalApplied(rebalanceDTO.getIdealTotalApplied());
        rebalance.setIdealPercentWallet(rebalanceDTO.getIdealPercentWallet());
        rebalance.setIdealAmount(rebalanceDTO.getIdealAmount());
        rebalance.setAdValueApply(rebalanceDTO.getAdValueApply());
        rebalance.setAdPercentWallet(rebalanceDTO.getAdPercentWallet());
        rebalance.setAdAmount(rebalanceDTO.getAdAmount());
        rebalance.setStatus(rebalanceDTO.getStatus());

        Investiment investiment = rebalance.getInvestiment();
        investiment.setAmount(rebalanceDTO.getAmount());
        rebalance.setInvestiment(investiment);

        return rebalance;
    }

}