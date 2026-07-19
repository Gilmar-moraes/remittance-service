package br.inter.dti.gmoraes.remittance.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cotacao {

    private Long id;

    private BigDecimal cotacaoCompra;

    private LocalDateTime dataHoraCotacao;

    public Cotacao() {}

    public Cotacao(BigDecimal cotacaoCompra, LocalDateTime dataHoraCotacao) {
        this.cotacaoCompra = cotacaoCompra;
        this.dataHoraCotacao = dataHoraCotacao;
    }

    public Long getId() {
        return id;
    }
    public LocalDateTime getDataHoraCotacao() {
        return dataHoraCotacao;
    }

    public BigDecimal getCotacaoCompra() {
        return cotacaoCompra;
    }
}
