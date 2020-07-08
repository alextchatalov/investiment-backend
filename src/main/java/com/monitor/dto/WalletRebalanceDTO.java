package com.monitor.dto;

import com.monitor.domain.Investiment;
import com.monitor.domain.enums.InvestimentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Getter
public class WalletRebalanceDTO {

    private Long id;
    private Investiment investiment;
    private Integer note;
    private BigDecimal percentWallet;
    private BigDecimal idealTotalApplied;
    private BigDecimal idealPercentWallet;
    private Integer ideal_amount;
    private BigDecimal adValueApply;
    private BigDecimal adPercentWallet;
    private Integer adAmount;
    private InvestimentStatus status;

}
