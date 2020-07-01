package com.monitor.domain.enums;

public enum InvestimentStatus {

    COMPRAR("Comprar"),
    AGUARDAR("Aguardar"),
    VENDER("Vender");
    private String description;

    InvestimentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
