package com.monitor.rest;

import com.monitor.domain.WalletRebalance;
import com.monitor.dto.InvestimentDTO;
import com.monitor.dto.WalletRebalanceDTO;
import com.monitor.service.WalletRebalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/rebalance")
public class WalletRebalanceRest {

    @Autowired
    private WalletRebalanceService service;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<WalletRebalanceDTO> getAll() throws IOException {
        List<WalletRebalance> rebalances = service.getAll();
        return castToDto(rebalances);
    }

    private List<WalletRebalanceDTO> castToDto(List<WalletRebalance> rebalances) {

        List<WalletRebalanceDTO> walletRebalanceDTOs = new ArrayList<>();
        rebalances.forEach(rebalance -> {
            WalletRebalanceDTO rebalanceDTO = WalletRebalanceDTO.builder()
                    .id(rebalance.getId())
                    .investiment(rebalance.getInvestiment())
                    .note(rebalance.getNote())
                    .percentWallet(rebalance.getPercentWallet())
                    .idealTotalApplied(rebalance.getIdealTotalApplied())
                    .idealPercentWallet(rebalance.getIdealPercentWallet())
                    .ideal_amount(rebalance.getIdeal_amount())
                    .adValueApply(rebalance.getAdValueApply())
                    .adPercentWallet(rebalance.getAdPercentWallet())
                    .adAmount(rebalance.getAdAmount())
                    .status(rebalance.getStatus())
                    .build();
            walletRebalanceDTOs.add(rebalanceDTO);
        });
        return walletRebalanceDTOs;
    }

}