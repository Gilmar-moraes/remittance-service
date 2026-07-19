package br.inter.dti.gmoraes.remittance.application.dto;

import java.math.BigDecimal;

public record RealizarRemessaDTO(
        Long remetenteId,

        Long destinatarioId,

        BigDecimal valorReal
) {
}
