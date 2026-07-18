package br.inter.dti.gmoraes.remittance.application.dto;

import br.inter.dti.gmoraes.remittance.domain.enums.TipoUsuario;

public record CriarUsuarioCommand(
        String nomeCompleto,
        String email,
        String senha,
        String cpf,
        String cnpj,
        TipoUsuario tipoUsuario
) {
}
