package br.inter.dti.gmoraes.remittance.application.service;

import br.inter.dti.gmoraes.remittance.application.dto.CriarUsuarioDTO;
import br.inter.dti.gmoraes.remittance.application.port.out.UsuarioRepositoryPort;
import br.inter.dti.gmoraes.remittance.application.validator.UsuarioValidator;
import br.inter.dti.gmoraes.remittance.domain.enums.TipoUsuario;
import br.inter.dti.gmoraes.remittance.domain.exception.UsuarioJaExisteException;
import br.inter.dti.gmoraes.remittance.domain.model.Usuario;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CriarUsuarioServiceTest {
    @Mock
    private UsuarioRepositoryPort usuarioRepository;

    @Mock
    private UsuarioValidator usuarioValidator;

    @InjectMocks
    private CriarUsuarioService service;

    @Test
   public void deveCriarUsuarioComSucesso() {

        CriarUsuarioDTO dto = new CriarUsuarioDTO(
                "Gilmar",
                "gilmar@email.com",
                "123456",
                "12345678900",
                null,
                TipoUsuario.PF
        );

        when(usuarioRepository.salvar(any())).thenAnswer(invocation -> {
            Usuario usuario = invocation.getArgument(0);
            usuario.setId(1L);
            return usuario;
        });

        Usuario usuario = service.criar(dto);

        assertNotNull(usuario);

        assertEquals("Gilmar", usuario.getNomeCompleto());

        assertEquals(BigDecimal.ZERO,usuario.getCarteira().getSaldoReal());

        assertEquals(BigDecimal.ZERO,usuario.getCarteira().getSaldoDolar());

        verify(usuarioValidator).validar(dto);

        verify(usuarioRepository).salvar(any());
    }

    @Test
    void deveExecutarValidacaoAntesDeSalvar() {

        CriarUsuarioDTO dto = new CriarUsuarioDTO(
                "Gilmar",
                "gilmar@email.com",
                "123456",
                "12345678900",
                null,
                TipoUsuario.PF
        );

        when(usuarioRepository.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));

        service.criar(dto);

        verify(usuarioValidator).validar(dto);
    }

    @Test
    public void naoDeveSalvarQuandoValidacaoFalhar() {

        CriarUsuarioDTO dto = new CriarUsuarioDTO(
                "Gilmar",
                "gilmar@email.com",
                "123456",
                "12345678900",
                null,
                TipoUsuario.PF
        );

        doThrow(new UsuarioJaExisteException("E-mail já cadastrado.")).when(usuarioValidator).validar(dto);

        assertThrows(UsuarioJaExisteException.class,() -> service.criar(dto));

        verify(usuarioRepository, never())
                .salvar(any());
    }

}
