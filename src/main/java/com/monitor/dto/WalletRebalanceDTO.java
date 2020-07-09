package com.monitor.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.monitor.domain.Investiment;
import com.monitor.domain.enums.InvestimentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Getter
@JsonAutoDetect
public class WalletRebalanceDTO {
    private Long id;
    private Investiment investiment;
    private Integer note;
    private BigDecimal percentWallet;
    private BigDecimal idealTotalApplied;
    private BigDecimal idealPercentWallet;
    private Integer idealAmount;
    private BigDecimal adValueApply;
    private BigDecimal adPercentWallet;
    private Integer adAmount;
    private InvestimentStatus status;

}
