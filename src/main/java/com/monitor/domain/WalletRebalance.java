package com.monitor.domain;

import com.monitor.domain.enums.InvestimentStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class WalletRebalance {

    // Posicao atual
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "investiment_code", referencedColumnName = "investiment_code")
    private Investiment investiment;
    @Column(name = "note")
    private Integer note;
    @Column(name = "percent_wallet")
    private BigDecimal percentWallet;
    // Posicao desejada
    @Column(name = "ideal_total_applied")
    private BigDecimal idealTotalApplied;
    @Column(name = "ideal_percent_wallet")
    private BigDecimal idealPercentWallet;
    @Column(name = "ideal_amount")
    private Integer idealAmount;
    // Ajuste de Posicao
    @Column(name = "ad_value_apply")
    private BigDecimal adValueApply;
    @Column(name = "ad_percent_wallet")
    private BigDecimal adPercentWallet;
    @Column(name = "ad_amount")
    private Integer adAmount;
    @Enumerated(EnumType.STRING)
    private InvestimentStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Investiment getInvestiment() {
        return investiment;
    }

    public void setInvestiment(Investiment investiment) {
        this.investiment = investiment;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public BigDecimal getPercentWallet() {
        return percentWallet;
    }

    public void setPercentWallet(BigDecimal percentWallet) {
        this.percentWallet = percentWallet;
    }

    public BigDecimal getIdealTotalApplied() {
        return idealTotalApplied;
    }

    public void setIdealTotalApplied(BigDecimal idealTotalApplied) {
        this.idealTotalApplied = idealTotalApplied;
    }

    public BigDecimal getIdealPercentWallet() {
        return idealPercentWallet;
    }

    public void setIdealPercentWallet(BigDecimal idealPercentWallet) {
        this.idealPercentWallet = idealPercentWallet;
    }

    public Integer getIdealAmount() {
        return idealAmount;
    }

    public void setIdealAmount(Integer idealAmount) {
        this.idealAmount = idealAmount;
    }

    public BigDecimal getAdValueApply() {
        return adValueApply;
    }

    public void setAdValueApply(BigDecimal adValueApply) {
        this.adValueApply = adValueApply;
    }

    public BigDecimal getAdPercentWallet() {
        return adPercentWallet;
    }

    public void setAdPercentWallet(BigDecimal adPercentWallet) {
        this.adPercentWallet = adPercentWallet;
    }

    public Integer getAdAmount() {
        return adAmount;
    }

    public void setAdAmount(Integer adAmount) {
        this.adAmount = adAmount;
    }

    public InvestimentStatus getStatus() {
        return status;
    }

    public void setStatus(InvestimentStatus status) {
        this.status = status;
    }
}
