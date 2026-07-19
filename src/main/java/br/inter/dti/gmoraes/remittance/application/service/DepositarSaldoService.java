package br.inter.dti.gmoraes.remittance.application.service;

import br.inter.dti.gmoraes.remittance.application.dto.DepositarSaldoDTO;
import br.inter.dti.gmoraes.remittance.application.port.in.DepositarSaldoUseCase;
import br.inter.dti.gmoraes.remittance.application.port.out.UsuarioRepositoryPort;
import br.inter.dti.gmoraes.remittance.domain.enums.TipoMoeda;
import br.inter.dti.gmoraes.remittance.domain.exception.UsuarioNaoEncontradoException;
import br.inter.dti.gmoraes.remittance.domain.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepositarSaldoService implements DepositarSaldoUseCase {

    private final UsuarioRepositoryPort usuarioRepository;

    public DepositarSaldoService(
            UsuarioRepositoryPort usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void depositar(DepositarSaldoDTO depositarSaldoDTO) {

        Usuario usuario = usuarioRepository.buscarPorId(depositarSaldoDTO.usuarioId())
                .orElseThrow(() ->
                        new UsuarioNaoEncontradoException(depositarSaldoDTO.usuarioId()));

        if (depositarSaldoDTO.moeda() == TipoMoeda.BRL) {

            usuario.getCarteira()
                    .creditarReal(depositarSaldoDTO.valor());

            return;
        }

        usuario.getCarteira()
                .creditarDolar(depositarSaldoDTO.valor());

    }

}