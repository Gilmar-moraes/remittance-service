package br.inter.dti.gmoraes.remittance.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "remessas")
public class Remessa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Usuario remetente;

    @ManyToOne(optional = false)
    private Usuario destinatario;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorReal;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valorDolar;

    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal cotacaoCompra;

    @Column(nullable = false)
    private LocalDate dataCotacao;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    public Remessa() {}

    public Remessa(LocalDateTime dataHora,
                   LocalDate dataCotacao,
                   BigDecimal cotacaoCompra,
                   BigDecimal valorDolar,
                   BigDecimal valorReal,
                   Usuario destinatario,
                   Usuario remetente,
                   Long id) {
        this.dataHora = dataHora;
        this.dataCotacao = dataCotacao;
        this.cotacaoCompra = cotacaoCompra;
        this.valorDolar = valorDolar;
        this.valorReal = valorReal;
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.id = id;
    }

    public LocalDate getDataCotacao() {
        return dataCotacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public BigDecimal getCotacaoCompra() {
        return cotacaoCompra;
    }

    public BigDecimal getValorDolar() {
        return valorDolar;
    }

    public BigDecimal getValorReal() {
        return valorReal;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public Long getId() {
        return id;
    }

    public static Remessa registrar(
            Usuario remetente,
            Usuario destinatario,
            BigDecimal valorReal,
            BigDecimal valorDolar,
            BigDecimal cotacaoCompra,
            LocalDate dataCotacao) {

        Remessa remessa = new Remessa();

        remessa.remetente = remetente;
        remessa.destinatario = destinatario;
        remessa.valorReal = valorReal;
        remessa.valorDolar = valorDolar;
        remessa.cotacaoCompra = cotacaoCompra;
        remessa.dataCotacao = dataCotacao;
        remessa.dataHora = LocalDateTime.now();

        return remessa;
    }
}