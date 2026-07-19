package br.inter.dti.gmoraes.remittance.application.port.out;

import br.inter.dti.gmoraes.remittance.domain.model.Remessa;


public interface RemessaRepositoryPort {

    public Remessa salvar(Remessa remessa);
}
