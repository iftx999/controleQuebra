package com.example.perdas.model;

import java.math.BigDecimal;

public class DashboardDTO {

    private BigDecimal valorTotalQuebras;
    private long quantidadeTotalQuebras;
    private String funcionarioMaisQuebrou;

    public DashboardDTO(BigDecimal valorTotalQuebras, long quantidadeTotalQuebras, String funcionarioMaisQuebrou) {
        this.valorTotalQuebras = valorTotalQuebras;
        this.quantidadeTotalQuebras = quantidadeTotalQuebras;
        this.funcionarioMaisQuebrou = funcionarioMaisQuebrou;
    }

    public BigDecimal getValorTotalQuebras() {
        return valorTotalQuebras;
    }

    public long getQuantidadeTotalQuebras() {
        return quantidadeTotalQuebras;
    }

    public String getFuncionarioMaisQuebrou() {
        return funcionarioMaisQuebrou;
    }
}


