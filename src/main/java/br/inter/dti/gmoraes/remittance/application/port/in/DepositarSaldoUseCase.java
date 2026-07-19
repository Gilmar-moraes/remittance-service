package br.inter.dti.gmoraes.remittance.application.port.in;

import br.inter.dti.gmoraes.remittance.application.dto.DepositarSaldoDTO;

public interface DepositarSaldoUseCase {
   void depositar(DepositarSaldoDTO depositarSaldoDTO);
}
