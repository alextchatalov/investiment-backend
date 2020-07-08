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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public List<WalletRebalanceDTO> getAll() {
        List<WalletRebalance> rebalances = service.getAll();
        return castToDto(rebalances);
    }

    @PostMapping("/update")
    public void updateRebalance(@RequestParam("rebalance") WalletRebalanceDTO rebalanceDTO) {
        try {
            System.out.println(rebalanceDTO);
            service.save(castToEntity(rebalanceDTO));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private WalletRebalance castToEntity(WalletRebalanceDTO rebalanceDTO) {
        WalletRebalance rebalance = new WalletRebalance();
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
        return rebalance;
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
                    .idealAmount(rebalance.getIdealAmount())
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