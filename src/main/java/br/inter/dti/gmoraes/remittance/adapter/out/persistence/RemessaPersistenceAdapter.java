package br.inter.dti.gmoraes.remittance.adapter.out.persistence;

import br.inter.dti.gmoraes.remittance.adapter.out.persistence.repository.SpringDataRemessaRepository;
import br.inter.dti.gmoraes.remittance.application.port.out.RemessaRepositoryPort;
import br.inter.dti.gmoraes.remittance.domain.model.Remessa;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class RemessaPersistenceAdapter implements RemessaRepositoryPort {

    private final SpringDataRemessaRepository repository;

    public RemessaPersistenceAdapter(SpringDataRemessaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Remessa salvar(Remessa remessa) {
        return repository.save(remessa);
    }

    @Override
    public BigDecimal somarValorRemessasNoPeriodo(
            Long usuarioId,
            LocalDateTime inicio,
            LocalDateTime fim) {

        return repository.somarValorRemessasNoPeriodo(
                usuarioId,
                inicio,
                fim
        );
    }
}
