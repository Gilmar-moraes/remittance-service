package br.inter.dti.gmoraes.remittance.adapter.in.web.dto;

import java.math.BigDecimal;

public record RealizarRemessaResponse(

        Long id,

        BigDecimal valorReal,

        BigDecimal valorDolar
) {
}
