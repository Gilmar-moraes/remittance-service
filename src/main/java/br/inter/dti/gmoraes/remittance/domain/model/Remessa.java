package br.inter.dti.gmoraes.remittance.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
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

    @ManyToOne(optional = false)
    private Cotacao cotacao;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    public Remessa() {}

    public Remessa(Long id,
                   Usuario remetente,
                   Usuario destinatario,
                   BigDecimal valorReal,
                   BigDecimal valorDolar,
                   Cotacao cotacao,
                   LocalDateTime dataHora) {
        this.id = id;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.valorReal = valorReal;
        this.valorDolar = valorDolar;
        this.cotacao = cotacao;
        this.dataHora = dataHora;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Cotacao getCotacao() {
        return cotacao;
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
            Cotacao cotacao) {

        Remessa remessa = new Remessa();

        remessa.remetente = remetente;
        remessa.destinatario = destinatario;
        remessa.valorReal = valorReal;
        remessa.valorDolar = valorDolar;
        remessa.cotacao = cotacao;
        remessa.dataHora = LocalDateTime.now();

        return remessa;
    }
}