package br.inter.dti.gmoraes.remittance.adapter.in.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RealizarRemessaRequest(

        @NotNull
        Long remetenteId,

        @NotNull
        Long destinatarioId,

        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal valorReal
) {
}