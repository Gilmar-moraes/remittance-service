package br.inter.dti.gmoraes.remittance.application.dto;

import br.inter.dti.gmoraes.remittance.domain.enums.TipoMoeda;

import java.math.BigDecimal;

public record DepositarSaldoDTO(

        Long usuarioId,

        BigDecimal valor,

        TipoMoeda moeda

) {
}
