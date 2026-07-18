package br.inter.dti.gmoraes.remittance.domain.exception;

public class UsuarioNaoEncontradoException extends RegraNegocioException  {

    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário com id %d não encontrado.".formatted(id));
    }
}
