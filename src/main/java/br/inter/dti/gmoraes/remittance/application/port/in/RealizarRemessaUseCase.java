package br.inter.dti.gmoraes.remittance.application.port.in;

import br.inter.dti.gmoraes.remittance.application.dto.RealizarRemessaCommand;

public interface RealizarRemessaUseCase {
    public void realizar(RealizarRemessaCommand remessaCommand);
}
