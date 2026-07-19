package br.inter.dti.gmoraes.remittance.application.port.out;

import br.inter.dti.gmoraes.remittance.domain.model.Remessa;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public interface RemessaRepositoryPort {

    Remessa salvar(Remessa remessa);

    BigDecimal somarValorRemessasNoPeriodo(
            Long usuarioId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
