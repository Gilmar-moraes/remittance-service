package br.inter.dti.gmoraes.remittance.application.port.in;

import br.inter.dti.gmoraes.remittance.application.dto.RealizarRemessaDTO;
import br.inter.dti.gmoraes.remittance.domain.model.Remessa;

public interface RealizarRemessaUseCase {

    public Remessa realizar(RealizarRemessaDTO remessaDTOCommand);
}
