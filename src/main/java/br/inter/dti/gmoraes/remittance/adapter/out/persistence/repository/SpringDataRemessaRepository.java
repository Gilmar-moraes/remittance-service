package br.inter.dti.gmoraes.remittance.adapter.out.persistence.repository;

import br.inter.dti.gmoraes.remittance.domain.model.Remessa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface SpringDataRemessaRepository extends JpaRepository<Remessa, Long> {

    @Query("""
        SELECT COALESCE(SUM(r.valorReal),0)
        FROM Remessa r
        WHERE r.remetente.id = :usuarioId
        AND r.dataHora BETWEEN :inicio AND :fim
        """)
    BigDecimal somarValorRemessasNoPeriodo(
            Long usuarioId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
