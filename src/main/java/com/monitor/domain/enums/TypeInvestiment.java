package com.monitor.domain.enums;

public enum TypeInvestiment {

    ACAO("Ação"),
    FUNDOS_IMOBILIARIOS("Fundo Imobiliário"),
    TESOURO_DIRETO("Tesouro Direto"),
    CONTA_CORRENTE("Conta Corrente"),
    OTHER("Outro");
    private String description;

    TypeInvestiment(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static TypeInvestiment fromString(String text) {
        for (TypeInvestiment b : TypeInvestiment.values()) {
            if (b.description.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
