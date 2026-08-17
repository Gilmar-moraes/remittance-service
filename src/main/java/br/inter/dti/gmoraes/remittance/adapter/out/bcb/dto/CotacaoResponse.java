package br.inter.dti.gmoraes.remittance.adapter.out.bcb.dto;

import java.math.BigDecimal;

public record CotacaoResponse(
        BigDecimal cotacaoCompra,

        String dataHoraCotacao
) {
}
