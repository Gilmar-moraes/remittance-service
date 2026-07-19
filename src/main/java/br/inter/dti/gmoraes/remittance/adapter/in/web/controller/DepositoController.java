package br.inter.dti.gmoraes.remittance.adapter.in.web.controller;

import br.inter.dti.gmoraes.remittance.adapter.in.web.dto.DepositarSaldoRequest;
import br.inter.dti.gmoraes.remittance.application.dto.DepositarSaldoDTO;
import br.inter.dti.gmoraes.remittance.application.port.in.DepositarSaldoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class DepositoController {

    private final DepositarSaldoUseCase useCase;

    public DepositoController(
            DepositarSaldoUseCase useCase) {

        this.useCase = useCase;
    }

    @Operation(
            summary = "Depositar saldo",
            description = "Realiza um depósito em Real na carteira do usuário."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Depósito realizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PostMapping("/{id}/depositos")
    public ResponseEntity<Void> depositar(@PathVariable Long id, @Valid @RequestBody DepositarSaldoRequest request) {

        useCase.depositar(new DepositarSaldoDTO(id,request.valor(),request.moeda()));

        return ResponseEntity.noContent().build();
    }

}
