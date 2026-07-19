package br.inter.dti.gmoraes.remittance.application.service;

import br.inter.dti.gmoraes.remittance.application.dto.RealizarRemessaDTO;
import br.inter.dti.gmoraes.remittance.application.port.in.RealizarRemessaUseCase;
import br.inter.dti.gmoraes.remittance.application.port.out.CotacaoClientPort;
import br.inter.dti.gmoraes.remittance.application.port.out.RemessaRepositoryPort;
import br.inter.dti.gmoraes.remittance.application.port.out.UsuarioRepositoryPort;
import br.inter.dti.gmoraes.remittance.domain.enums.TipoUsuario;
import br.inter.dti.gmoraes.remittance.domain.exception.RegraNegocioException;
import br.inter.dti.gmoraes.remittance.domain.exception.UsuarioNaoEncontradoException;
import br.inter.dti.gmoraes.remittance.domain.model.Cotacao;
import br.inter.dti.gmoraes.remittance.domain.model.Remessa;
import br.inter.dti.gmoraes.remittance.domain.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
public class RealizarRemessaService implements RealizarRemessaUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final RemessaRepositoryPort remessaRepository;
    private final CotacaoClientPort cotacaoClientPort;

    public RealizarRemessaService(UsuarioRepositoryPort usuarioRepository,
                                  RemessaRepositoryPort remessaRepository,
                                  CotacaoClientPort cotacaoClientPort) {
        this.usuarioRepository = usuarioRepository;
        this.remessaRepository = remessaRepository;
        this.cotacaoClientPort = cotacaoClientPort;
    }

    @Override
    public Remessa realizar(RealizarRemessaDTO remessaDTO) {

        Usuario remetente = buscarUsuario(remessaDTO.remetenteId());

        Usuario destinatario = buscarUsuario(remessaDTO.destinatarioId());

        validarRemessa(
                remetente,
                destinatario,
                remessaDTO.valorReal());

        Cotacao cotacao =
                cotacaoClientPort.buscarCotacao(LocalDate.now());

        BigDecimal valorDolar = converter(remessaDTO.valorReal(),cotacao.getCotacaoCompra());

        movimentarCarteiras(
                remetente,
                destinatario,
                remessaDTO.valorReal(),
                valorDolar);

        Remessa remessa =
                Remessa.registrar(
                        remetente,
                        destinatario,
                        remessaDTO.valorReal(),
                        valorDolar,
                        cotacao.getCotacaoCompra(),
                        cotacao.getDataHoraCotacao().toLocalDate()
                );

        return remessaRepository.salvar(remessa);
    }

    private Usuario buscarUsuario(Long id) {

        return usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    private void validarRemessa(
            Usuario remetente,
            Usuario destinatario,
            BigDecimal valor) {

        if (remetente.getId().equals(destinatario.getId())) {
            throw new RegraNegocioException(
                    "O remetente não pode ser o destinatário."
            );
        }

        if (!remetente.getCarteira().possuiSaldoEmReal(valor)) {
            throw new RegraNegocioException(
                    "Saldo insuficiente."
            );
        }

        validarLimiteDiario(
                remetente,
                valor
        );
    }

    private void movimentarCarteiras(
            Usuario remetente,
            Usuario destinatario,
            BigDecimal valorReal,
            BigDecimal valorDolar) {

        remetente.getCarteira()
                .debitarReal(valorReal);

        destinatario.getCarteira()
                .creditarDolar(valorDolar);
    }

    private BigDecimal converter(
            BigDecimal valorReal,
            BigDecimal cotacao) {

        return valorReal.divide(
                cotacao,
                2,
                RoundingMode.HALF_EVEN
        );

    }

    private void validarLimiteDiario(
            Usuario remetente,
            BigDecimal valorRemessa) {

        LocalDate hoje = LocalDate.now();

        BigDecimal totalHoje =
                remessaRepository.somarValorRemessasNoPeriodo(
                        remetente.getId(),
                        hoje.atStartOfDay(),
                        hoje.atTime(LocalTime.MAX)
                );

        BigDecimal limite =
                remetente.getTipoUsuario() == TipoUsuario.PF
                        ? new BigDecimal("10000")
                        : new BigDecimal("50000");

        if (totalHoje.add(valorRemessa).compareTo(limite) > 0) {
            throw new RegraNegocioException(
                    "Limite diário de transações excedido."
            );
        }

    }
}
