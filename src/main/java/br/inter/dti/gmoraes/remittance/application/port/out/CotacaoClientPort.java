package br.inter.dti.gmoraes.remittance.application.port.out;

import br.inter.dti.gmoraes.remittance.domain.model.Cotacao;

import java.time.LocalDate;

public interface CotacaoClientPort {

    Cotacao buscarCotacao(LocalDate data);
}
