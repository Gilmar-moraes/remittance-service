package br.inter.dti.gmoraes.remittance.application.port.out;

import br.inter.dti.gmoraes.remittance.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {

    public Usuario salvar(Usuario usuario);

    public Optional<Usuario> buscarPorId(Long id);

    public Optional<Usuario> buscarPorEmail(String email);

    public Optional<Usuario> buscarPorCpf(String cpf);

    public Optional<Usuario> buscarPorCnpj(String cnpj);
}
