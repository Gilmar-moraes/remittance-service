package br.inter.dti.gmoraes.remittance.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cotacoes")
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal cotacaoCompra;

    @Column(nullable = false)
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
