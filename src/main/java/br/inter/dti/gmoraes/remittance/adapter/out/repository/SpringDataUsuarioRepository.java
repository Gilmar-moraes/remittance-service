package br.inter.dti.gmoraes.remittance.adapter.out.repository;

import br.inter.dti.gmoraes.remittance.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUsuarioRepository
        extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> findByCpf(String cpf);

    public Optional<Usuario> findByCnpj(String cnpj);

}
