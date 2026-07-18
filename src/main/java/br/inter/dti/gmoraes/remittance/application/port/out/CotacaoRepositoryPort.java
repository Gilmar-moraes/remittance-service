package br.inter.dti.gmoraes.remittance.application.port.out;

import br.inter.dti.gmoraes.remittance.domain.model.Cotacao;

import java.time.LocalDate;
import java.util.Optional;

public interface CotacaoRepositoryPort {

    Cotacao salvar(Cotacao cotacao);

    Optional<Cotacao> buscarPorData(LocalDate data);

    Optional<Cotacao> buscarUltimaCotacao();
}
