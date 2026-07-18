package br.inter.dti.gmoraes.remittance.application.service;

import br.inter.dti.gmoraes.remittance.application.dto.CriarUsuarioCommand;
import br.inter.dti.gmoraes.remittance.application.port.in.CriarUsuarioUseCase;
import br.inter.dti.gmoraes.remittance.application.port.out.UsuarioRepositoryPort;
import br.inter.dti.gmoraes.remittance.domain.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CriarUsuarioService implements CriarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepository;

    public CriarUsuarioService(UsuarioRepositoryPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario criar(CriarUsuarioCommand usuarioCommand) {

        Usuario usuario = Usuario.criar(usuarioCommand);

        return usuarioRepository.salvar(usuario);
    }
}
