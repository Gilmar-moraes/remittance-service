package br.inter.dti.gmoraes.remittance.adapter.in.web.controller;

import br.inter.dti.gmoraes.remittance.adapter.in.web.dto.RealizarRemessaRequest;
import br.inter.dti.gmoraes.remittance.adapter.in.web.dto.RealizarRemessaResponse;
import br.inter.dti.gmoraes.remittance.application.dto.RealizarRemessaDTO;
import br.inter.dti.gmoraes.remittance.application.port.in.RealizarRemessaUseCase;
import br.inter.dti.gmoraes.remittance.domain.model.Remessa;
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

    @PostMapping
    public ResponseEntity<RealizarRemessaResponse> realizar(
            @Valid @RequestBody RealizarRemessaRequest request) {

        RealizarRemessaDTO command =
                new RealizarRemessaDTO(
                        request.remetenteId(),
                        request.destinatarioId(),
                        request.valorReal()
                );

        Remessa remessa = useCase.realizar(command);

        RealizarRemessaResponse response =
                new RealizarRemessaResponse(
                        remessa.getId(),
                        remessa.getValorReal(),
                        remessa.getValorDolar()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
