package br.inter.dti.gmoraes.remittance.adapter.in.web.handler;

import br.inter.dti.gmoraes.remittance.adapter.in.web.dto.ErroResponse;
import br.inter.dti.gmoraes.remittance.domain.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroResponse> tratarRegraNegocio(
            RegraNegocioException ex) {

        return ResponseEntity.badRequest()
                .body(
                        new ErroResponse(
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarValidacao(MethodArgumentNotValidException ex) {
        String erro = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity.badRequest().body(
                new ErroResponse(LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                erro)
                );
    }
}
