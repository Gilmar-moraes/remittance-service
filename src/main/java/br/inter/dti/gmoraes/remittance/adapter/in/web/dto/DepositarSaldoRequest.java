package br.inter.dti.gmoraes.remittance.adapter.in.web.dto;

import br.inter.dti.gmoraes.remittance.domain.enums.TipoMoeda;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DepositarSaldoRequest(

        @NotNull
        @Positive
        BigDecimal valor,

        @NotNull
        TipoMoeda moeda

) {
}
