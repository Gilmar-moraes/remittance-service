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

    public Carteira() {
        this.saldoReal = BigDecimal.ZERO;;
        this.saldoDolar = BigDecimal.ZERO;;
    }

    public Carteira(BigDecimal saldoReal, BigDecimal saldoDolar) {
        this.saldoReal = saldoReal;
        this.saldoDolar = saldoDolar;
    }

    public BigDecimal getSaldoReal() {
        return saldoReal;
    }

    public BigDecimal getSaldoDolar() {
        return saldoDolar;
    }

    public boolean possuiSaldoEmReal(BigDecimal valor) {
        return saldoReal.compareTo(valor) >= 0;
    }

    public void debitarReal(BigDecimal valor) {
        this.saldoReal = this.saldoReal.subtract(valor);
    }

    public void creditarReal(BigDecimal valor) {
        this.saldoReal = this.saldoReal.add(valor);
    }

    public void debitarDolar(BigDecimal valor) {
        this.saldoDolar = this.saldoDolar.subtract(valor);
    }

    public void creditarDolar(BigDecimal valor) {
        this.saldoDolar = this.saldoDolar.add(valor);
    }
}
