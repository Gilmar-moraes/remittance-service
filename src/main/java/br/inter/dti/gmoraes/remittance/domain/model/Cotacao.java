package br.inter.dti.gmoraes.remittance.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cotacoes")
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal valorCompra;

    @Column(nullable = false)
    private LocalDate dataCotacao;

    public Cotacao() {}

    public Cotacao(BigDecimal valorCompra, LocalDate dataCotacao) {
        this.valorCompra = valorCompra;
        this.dataCotacao = dataCotacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCotacao() {
        return dataCotacao;
    }

    public void setDataCotacao(LocalDate dataCotacao) {
        this.dataCotacao = dataCotacao;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }
}
