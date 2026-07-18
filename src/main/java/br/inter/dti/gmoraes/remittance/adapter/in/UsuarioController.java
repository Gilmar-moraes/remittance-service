package br.inter.dti.gmoraes.remittance.adapter.in;

import br.inter.dti.gmoraes.remittance.adapter.in.dto.CriarUsuarioRequest;
import br.inter.dti.gmoraes.remittance.adapter.in.dto.UsuarioResponse;
import br.inter.dti.gmoraes.remittance.application.dto.CriarUsuarioCommand;
import br.inter.dti.gmoraes.remittance.application.port.in.CriarUsuarioUseCase;
import br.inter.dti.gmoraes.remittance.domain.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;

    public UsuarioController(CriarUsuarioUseCase criarUsuarioUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(
            @RequestBody CriarUsuarioRequest request) {

        CriarUsuarioCommand command =
                new CriarUsuarioCommand(
                        request.nomeCompleto(),
                        request.email(),
                        request.senha(),
                        request.cpf(),
                        request.cnpj(),
                        request.tipoUsuario()
                );

        Usuario usuario = criarUsuarioUseCase.criar(command);

        UsuarioResponse response =
                new UsuarioResponse(
                        usuario.getId(),
                        usuario.getNomeCompleto(),
                        usuario.getEmail()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}