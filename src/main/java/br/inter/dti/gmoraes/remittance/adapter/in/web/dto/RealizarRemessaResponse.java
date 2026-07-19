package br.inter.dti.gmoraes.remittance.adapter.in.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record RealizarRemessaResponse(

        Long id,

        Long remetenteId,

        Long destinatarioId,

        BigDecimal valorReal,

        BigDecimal cotacaoCompra,

        BigDecimal valorDolar,

        LocalDateTime dataHora,

        String mensagem
) {
}
