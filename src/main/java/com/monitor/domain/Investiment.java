package com.monitor.domain;

import com.monitor.domain.enums.TypeInvestiment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Investiment {
    @Id
    @Column(name = "investiment_code")
    private String investimentCode;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeInvestiment type;
    @Column(name = "broker")
    private String broker;
    @Column(name = "first_date_application")
    private LocalDate firstDateApplication;
    @Column(name = "applied_amount")
    private BigDecimal appliedAmount;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "rentail")
    private BigDecimal rentail;
    @Column(name = "portfolio_share")
    private BigDecimal portfolioShare;

    public String getInvestimentCode() {
        return investimentCode;
    }

    public void setInvestimentCode(String investimentCode) {
        this.investimentCode = investimentCode;
    }

    public TypeInvestiment getType() {
        return type;
    }

    public void setType(TypeInvestiment type) {
        this.type = type;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public LocalDate getFirstDateApplication() {
        return firstDateApplication;
    }

    public void setFirstDateApplication(LocalDate firstDateApplication) {
        this.firstDateApplication = firstDateApplication;
    }

    public BigDecimal getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(BigDecimal appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getRentail() {
        return rentail;
    }

    public void setRentail(BigDecimal rentail) {
        this.rentail = rentail;
    }

    public BigDecimal getPortfolioShare() {
        return portfolioShare;
    }

    public void setPortfolioShare(BigDecimal portfolioShare) {
        this.portfolioShare = portfolioShare;
    }

    public boolean isStonkOrFII() {
        return TypeInvestiment.ACAO.equals(this.getType()) || TypeInvestiment.FUNDOS_IMOBILIARIOS.equals(this.getType());
    }
}
