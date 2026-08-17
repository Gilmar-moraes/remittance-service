package br.inter.dti.gmoraes.remittance.adapter.out.bcb.dto;

import java.math.BigDecimal;

public record BancoCentralResponse(

        String dataHoraCotacao,

        BigDecimal cotacaoCompra

) {
}
