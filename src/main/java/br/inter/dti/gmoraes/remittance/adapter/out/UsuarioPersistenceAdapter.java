package br.inter.dti.gmoraes.remittance.adapter.out;

import br.inter.dti.gmoraes.remittance.adapter.out.repository.SpringDataUsuarioRepository;
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
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Usuario> buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    @Override
    public Optional<Usuario> buscarPorCnpj(String cnpj) {
        return repository.findByCnpj(cnpj);
    }
}