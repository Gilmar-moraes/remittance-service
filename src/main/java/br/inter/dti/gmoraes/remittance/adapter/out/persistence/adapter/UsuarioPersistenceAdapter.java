package br.inter.dti.gmoraes.remittance.adapter.out.persistence.adapter;

import br.inter.dti.gmoraes.remittance.adapter.out.persistence.repository.SpringDataUsuarioRepository;
import br.inter.dti.gmoraes.remittance.application.port.out.UsuarioRepositoryPort;
import br.inter.dti.gmoraes.remittance.domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final SpringDataUsuarioRepository repository;

    public UsuarioPersistenceAdapter(
            SpringDataUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return null;
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> buscarPorCpf(String cpf) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> buscarPorCnpj(String cnpj) {
        return Optional.empty();
    }
}