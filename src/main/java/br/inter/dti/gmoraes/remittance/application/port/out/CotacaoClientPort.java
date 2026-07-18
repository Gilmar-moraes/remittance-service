package br.inter.dti.gmoraes.remittance.application.port.out;

import br.inter.dti.gmoraes.remittance.domain.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CotacaoClientPort {

    public BigDecimal consultarCotacao(LocalDate data);
}
