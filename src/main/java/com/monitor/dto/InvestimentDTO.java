package com.monitor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Getter
public class InvestimentDTO {

    private String investimentCode;
    private String type;
    private String broker;
    private LocalDate firstDateApplication;
    private BigDecimal appliedAmount;
    private BigDecimal balance;
    private BigDecimal rentail;
    private BigDecimal portfolioShare;

}
