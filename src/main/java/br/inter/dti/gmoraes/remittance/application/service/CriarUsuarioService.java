package br.inter.dti.gmoraes.remittance.application.service;

import br.inter.dti.gmoraes.remittance.application.dto.CriarUsuarioDTO;
import br.inter.dti.gmoraes.remittance.application.port.in.CriarUsuarioUseCase;
import br.inter.dti.gmoraes.remittance.application.port.out.UsuarioRepositoryPort;
import br.inter.dti.gmoraes.remittance.application.validator.UsuarioValidator;
import br.inter.dti.gmoraes.remittance.domain.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CriarUsuarioService implements CriarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepository;

    private final UsuarioValidator usuarioValidator;

    public CriarUsuarioService(UsuarioRepositoryPort usuarioRepository,
                               UsuarioValidator usuarioValidator) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioValidator = usuarioValidator;
    }

    @Override
    public Usuario criar(CriarUsuarioDTO usuarioDTO) {

        usuarioValidator.validar(usuarioDTO);

        Usuario usuario = Usuario.criar(usuarioDTO);

        return usuarioRepository.salvar(usuario);
    }
}
