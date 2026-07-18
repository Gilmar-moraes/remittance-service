package br.inter.dti.gmoraes.remittance.adapter.in.web.dto;

import java.time.LocalDateTime;

public record ErroResponse(
        LocalDateTime timestamp,
        Integer status,
        String erro
) {
}
