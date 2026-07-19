package br.inter.dti.gmoraes.remittance.application.service;

import br.inter.dti.gmoraes.remittance.application.dto.CriarUsuarioDTO;
import br.inter.dti.gmoraes.remittance.application.port.out.UsuarioRepositoryPort;
import br.inter.dti.gmoraes.remittance.application.validator.UsuarioValidator;
import br.inter.dti.gmoraes.remittance.domain.enums.TipoUsuario;
import br.inter.dti.gmoraes.remittance.domain.exception.DocumentoJaExisteException;
import br.inter.dti.gmoraes.remittance.domain.exception.DocumentoObrigatorioException;
import br.inter.dti.gmoraes.remittance.domain.exception.UsuarioJaExisteException;
import br.inter.dti.gmoraes.remittance.domain.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioValidatorTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepository;

    @InjectMocks
    private UsuarioValidator validator;

    @Test
   public void deveLancarExcecaoQuandoEmailJaExistir() {

        CriarUsuarioDTO dto = new CriarUsuarioDTO(
                "Gilmar",
                "gilmar@email.com",
                "123456",
                "12345678900",
                null,
                TipoUsuario.PF
        );

        when(usuarioRepository.buscarPorEmail(dto.email())).thenReturn(Optional.of(criarUsuario()));

        assertThrows( UsuarioJaExisteException.class, () -> validator.validar(dto));
    }

    @Test
    public void deveLancarExcecaoQuandoCpfNaoInformado() {

        CriarUsuarioDTO dto =new CriarUsuarioDTO(
                "Gilmar",
                "gilmar@email.com",
                "123456",
                "",
                null,
                TipoUsuario.PF
        );

        assertThrows(DocumentoObrigatorioException.class,() -> validator.validar(dto));
    }

    @Test
    public void deveLancarExcecaoQuandoCpfJaExistir() {

        CriarUsuarioDTO dto = new CriarUsuarioDTO(
                "Gilmar",
                "gilmar@email.com",
                "123456",
                "12345678900",
                null,
                TipoUsuario.PF
        );

        when(usuarioRepository.buscarPorCpf(dto.cpf())).thenReturn(Optional.of(criarUsuario()));

        assertThrows(DocumentoJaExisteException.class,() -> validator.validar(dto));
    }

    @Test
    public void deveLancarExcecaoQuandoCnpjNaoInformado() {

        CriarUsuarioDTO dto = new CriarUsuarioDTO(
                "Empresa",
                "empresa@email.com",
                "123456",
                null,
                "",
                TipoUsuario.PJ
        );

        assertThrows(DocumentoObrigatorioException.class,() -> validator.validar(dto));
    }

    @Test
    public void deveLancarExcecaoQuandoCnpjJaExistir() {

        CriarUsuarioDTO dto = new CriarUsuarioDTO(
                "Empresa",
                "empresa@email.com",
                "123456",
                null,
                "12345678000199",
                TipoUsuario.PJ
        );

        when(usuarioRepository.buscarPorCnpj(dto.cnpj())).thenReturn(Optional.of(criarUsuario()));

        assertThrows(DocumentoJaExisteException.class, () -> validator.validar(dto));
    }

    private Usuario criarUsuario() {

        Usuario usuario = Usuario.criar(new CriarUsuarioDTO(
                "Gilmar",
                "gilmar@email.com",
                "123456",
                "12345678900",
                null,
                TipoUsuario.PF)
        );

        usuario.setId(1L);

        return usuario;
    }
}
