package br.inter.dti.gmoraes.remittance.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class Carteira {

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldoReal;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldoDolar;

    public Carteira() {}

    public Carteira(BigDecimal saldoReal, BigDecimal saldoDolar) {
        this.saldoReal = saldoReal;
        this.saldoDolar = saldoDolar;
    }

    public BigDecimal getSaldoReal() {
        return saldoReal;
    }

    public void setSaldoReal(BigDecimal saldoReal) {
        this.saldoReal = saldoReal;
    }

    public BigDecimal getSaldoDolar() {
        return saldoDolar;
    }

    public void setSaldoDolar(BigDecimal saldoDolar) {
        this.saldoDolar = saldoDolar;
    }
}
