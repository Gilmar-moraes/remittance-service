package br.inter.dti.gmoraes.remittance.adapter.in.web.dto;

import br.inter.dti.gmoraes.remittance.domain.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarUsuarioRequest(
        @NotBlank(message = "Nome é obrigatório.")
        String nomeCompleto,

        @NotBlank(message = "E-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        String email,

        @NotBlank(message = "Senha é obrigatória.")
        String senha,

        @NotNull(message = "Tipo do usuário é obrigatório.")
        TipoUsuario tipoUsuario,

        String cpf,

        String cnpj
) {
}
