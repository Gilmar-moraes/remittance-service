package br.inter.dti.gmoraes.remittance.application.validator;

import br.inter.dti.gmoraes.remittance.application.dto.CriarUsuarioDTO;
import br.inter.dti.gmoraes.remittance.application.port.out.UsuarioRepositoryPort;
import br.inter.dti.gmoraes.remittance.domain.enums.TipoUsuario;
import br.inter.dti.gmoraes.remittance.domain.exception.DocumentoJaExisteException;
import br.inter.dti.gmoraes.remittance.domain.exception.DocumentoObrigatorioException;
import br.inter.dti.gmoraes.remittance.domain.exception.UsuarioJaExisteException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UsuarioValidator {

    private final UsuarioRepositoryPort usuarioRepository;

    public UsuarioValidator(UsuarioRepositoryPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void validar(CriarUsuarioDTO usuarioDTO) {

        validarEmail(usuarioDTO.email());

        validarDocumento(usuarioDTO);

    }

    private void validarEmail(String email) {

        if (usuarioRepository.buscarPorEmail(email).isPresent()) {
            throw new UsuarioJaExisteException(
                    "Já existe um usuário cadastrado com o e-mail informado."
            );
        }

    }

    private void validarDocumento(CriarUsuarioDTO usuarioDTO) {

        if (usuarioDTO.tipoUsuario() == TipoUsuario.PF) {

            if (!StringUtils.hasText(usuarioDTO.cpf())) {
                throw new DocumentoObrigatorioException("CPF");
            }

            if (usuarioRepository.buscarPorCpf(usuarioDTO.cpf()).isPresent()) {
                throw new DocumentoJaExisteException("CPF");
            }

            return;
        }

        if (!StringUtils.hasText(usuarioDTO.cnpj())) {
            throw new DocumentoObrigatorioException("CNPJ");
        }

        if (usuarioRepository.buscarPorCnpj(usuarioDTO.cnpj()).isPresent()) {
            throw new DocumentoJaExisteException("CNPJ");
        }
    }
}
