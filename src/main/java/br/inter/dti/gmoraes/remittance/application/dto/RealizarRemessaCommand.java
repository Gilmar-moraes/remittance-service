package br.inter.dti.gmoraes.remittance.application.dto;

import java.math.BigDecimal;

public record RealizarRemessaCommand(
        Long remetenteId,
        Long destinatarioId,
        BigDecimal valorReal
) {
}
