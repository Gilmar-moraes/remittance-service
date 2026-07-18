package br.inter.dti.gmoraes.remittance.application.port.in;

import br.inter.dti.gmoraes.remittance.application.dto.CriarUsuarioDTO;
import br.inter.dti.gmoraes.remittance.domain.model.Usuario;

public interface CriarUsuarioUseCase {

    public Usuario criar(CriarUsuarioDTO usuarioCommand);
}
