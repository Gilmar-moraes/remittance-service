package br.inter.dti.gmoraes.remittance.domain.exception;

public class UsuarioJaExisteException extends RegraNegocioException  {

    public UsuarioJaExisteException(String mensagem) {
        super(mensagem);
    }
}
