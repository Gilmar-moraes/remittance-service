package br.inter.dti.gmoraes.remittance.adapter.in.web.controller;

import br.inter.dti.gmoraes.remittance.adapter.in.web.dto.RealizarRemessaRequest;
import br.inter.dti.gmoraes.remittance.adapter.in.web.dto.RealizarRemessaResponse;
import br.inter.dti.gmoraes.remittance.application.dto.RealizarRemessaDTO;
import br.inter.dti.gmoraes.remittance.application.port.in.RealizarRemessaUseCase;
import br.inter.dti.gmoraes.remittance.domain.model.Remessa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/remessas")
public class RemessaController {

    private final RealizarRemessaUseCase useCase;

    public RemessaController(RealizarRemessaUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(
            summary = "Criar remessa",
            description = "Realiza uma remessa entre usuários PF e PJ com conversão de Real para Dólar utilizando a cotação oficial do Banco Central."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Remessa criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })

    @PostMapping
    public ResponseEntity<RealizarRemessaResponse> realizar(
            @Valid @RequestBody RealizarRemessaRequest request) {

        RealizarRemessaDTO remessaDTO =
                new RealizarRemessaDTO(
                        request.remetenteId(),
                        request.destinatarioId(),
                        request.valorReal()
                );

        Remessa remessa = useCase.realizar(remessaDTO);

        RealizarRemessaResponse response =
                new RealizarRemessaResponse(
                        remessa.getId(),
                        remessa.getRemetente().getId(),
                        remessa.getDestinatario().getId(),
                        remessa.getValorReal(),
                        remessa.getCotacaoCompra(),
                        remessa.getValorDolar(),
                        remessa.getDataHora(),
                        "Remessa realizada com sucesso."
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
