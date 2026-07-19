package br.inter.dti.gmoraes.remittance.application.service;

import br.inter.dti.gmoraes.remittance.application.dto.RealizarRemessaDTO;
import br.inter.dti.gmoraes.remittance.application.port.out.CotacaoClientPort;
import br.inter.dti.gmoraes.remittance.application.port.out.RemessaRepositoryPort;
import br.inter.dti.gmoraes.remittance.application.port.out.UsuarioRepositoryPort;
import br.inter.dti.gmoraes.remittance.domain.enums.TipoUsuario;
import br.inter.dti.gmoraes.remittance.domain.exception.RegraNegocioException;
import br.inter.dti.gmoraes.remittance.domain.exception.UsuarioNaoEncontradoException;
import br.inter.dti.gmoraes.remittance.domain.model.Carteira;
import br.inter.dti.gmoraes.remittance.domain.model.Cotacao;
import br.inter.dti.gmoraes.remittance.domain.model.Remessa;
import br.inter.dti.gmoraes.remittance.domain.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RealizarRemessaServiceTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepository;

    @Mock
    private RemessaRepositoryPort remessaRepository;

    @Mock
    private CotacaoClientPort cotacaoClientPort;

    @InjectMocks
    private RealizarRemessaService service;

    @Test
    void deveRealizarRemessaComSucesso() {

        Usuario remetente = criarUsuario(
                1L,
                "Gilmar",
                new BigDecimal("1000.00"),
                BigDecimal.ZERO
        );

        Usuario destinatario = criarUsuario(
                2L,
                "Maria",
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );

        Cotacao cotacao = criarCotacao();

        RealizarRemessaDTO dto = new RealizarRemessaDTO(1L,2L, new BigDecimal("100.00"));

        when(usuarioRepository.buscarPorId(1L)).thenReturn(Optional.of(remetente));

        when(usuarioRepository.buscarPorId(2L)).thenReturn(Optional.of(destinatario));

        when(cotacaoClientPort.buscarCotacao(any())).thenReturn(cotacao);

        when(remessaRepository.somarValorRemessasNoPeriodo(any(),any(),any())).thenReturn(BigDecimal.ZERO);

        when(remessaRepository.salvar(any(Remessa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Remessa remessa = service.realizar(dto);

        assertNotNull(remessa);

        assertEquals(new BigDecimal("100.00"),remessa.getValorReal());

        assertEquals(new BigDecimal("19.54"),remessa.getValorDolar());

        assertEquals(new BigDecimal("900.00"),remetente.getCarteira().getSaldoReal());

        assertEquals(new BigDecimal("19.54"),destinatario.getCarteira().getSaldoDolar());

        verify(remessaRepository).salvar(any(Remessa.class));
    }

    @Test
    void deveLancarExcecaoQuandoRemetenteNaoExistir() {

        RealizarRemessaDTO dto =
                new RealizarRemessaDTO(
                        1L,
                        2L,
                        new BigDecimal("100")
                );

        when(usuarioRepository.buscarPorId(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> service.realizar(dto)
        );

        verify(remessaRepository, never())
                .salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoDestinatarioNaoExistir() {

        Usuario remetente = criarUsuario(
                1L,
                "Gilmar",
                new BigDecimal("1000"),
                BigDecimal.ZERO
        );

        RealizarRemessaDTO dto =
                new RealizarRemessaDTO(
                        1L,
                        2L,
                        new BigDecimal("100")
                );

        when(usuarioRepository.buscarPorId(1L))
                .thenReturn(Optional.of(remetente));

        when(usuarioRepository.buscarPorId(2L))
                .thenReturn(Optional.empty());

        assertThrows(
                UsuarioNaoEncontradoException.class,
                () -> service.realizar(dto)
        );

        verify(remessaRepository, never())
                .salvar(any());
    }

    @Test
    void deveImpedirRemessaParaMesmoUsuario() {

        Usuario usuario = criarUsuario(
                1L,
                "Gilmar",
                new BigDecimal("1000"),
                BigDecimal.ZERO
        );

        RealizarRemessaDTO dto =
                new RealizarRemessaDTO(
                        1L,
                        1L,
                        new BigDecimal("100")
                );

        when(usuarioRepository.buscarPorId(1L))
                .thenReturn(Optional.of(usuario));

        assertThrows(
                RegraNegocioException.class,
                () -> service.realizar(dto)
        );

        verify(remessaRepository, never())
                .salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoSaldoInsuficiente() {

        Usuario remetente = criarUsuario(
                1L,
                "Gilmar",
                new BigDecimal("50"),
                BigDecimal.ZERO
        );

        Usuario destinatario = criarUsuario(
                2L,
                "Maria",
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );

        RealizarRemessaDTO dto =
                new RealizarRemessaDTO(
                        1L,
                        2L,
                        new BigDecimal("100")
                );

        when(usuarioRepository.buscarPorId(1L))
                .thenReturn(Optional.of(remetente));

        when(usuarioRepository.buscarPorId(2L))
                .thenReturn(Optional.of(destinatario));

        assertThrows(
                RegraNegocioException.class,
                () -> service.realizar(dto)
        );

        verify(remessaRepository, never())
                .salvar(any());
    }

    @Test
    void deveLancarExcecaoQuandoLimiteDiarioForExcedido() {

        Usuario remetente = criarUsuario(
                1L,
                "Gilmar",
                new BigDecimal("20000"),
                BigDecimal.ZERO
        );

        Usuario destinatario = criarUsuario(
                2L,
                "Maria",
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );

        RealizarRemessaDTO dto =
                new RealizarRemessaDTO(
                        1L,
                        2L,
                        new BigDecimal("100")
                );

        when(usuarioRepository.buscarPorId(1L))
                .thenReturn(Optional.of(remetente));

        when(usuarioRepository.buscarPorId(2L))
                .thenReturn(Optional.of(destinatario));

        when(remessaRepository.somarValorRemessasNoPeriodo(
                any(),
                any(),
                any()))
                .thenReturn(new BigDecimal("9950"));

        assertThrows(
                RegraNegocioException.class,
                () -> service.realizar(dto)
        );

        verify(remessaRepository, never())
                .salvar(any());
    }

    private Usuario criarUsuario(
            Long id,
            String nome,
            BigDecimal saldoReal,
            BigDecimal saldoDolar) {

        Usuario usuario = new Usuario(
                nome,
                nome.toLowerCase() + "@email.com",
                "123456",
                TipoUsuario.PF,
                "12345678900",
                null,
                new Carteira(saldoReal,saldoDolar));

        usuario.setId(id);

        return usuario;
    }

    private Cotacao criarCotacao() {

        return new Cotacao( new BigDecimal("5.117"),LocalDateTime.now());
    }
}