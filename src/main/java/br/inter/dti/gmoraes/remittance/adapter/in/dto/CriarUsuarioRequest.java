package br.inter.dti.gmoraes.remittance.adapter.in.dto;

import br.inter.dti.gmoraes.remittance.domain.enums.TipoUsuario;

public record CriarUsuarioRequest(
        String nomeCompleto,
        String email,
        String senha,
        TipoUsuario tipoUsuario,
        String cpf,
        String cnpj
) {
}
