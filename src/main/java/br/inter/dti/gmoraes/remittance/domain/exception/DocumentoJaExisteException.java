package br.inter.dti.gmoraes.remittance.domain.exception;

public class DocumentoJaExisteException extends RegraNegocioException {

    public DocumentoJaExisteException(String documento) {
        super(documento + " já está cadastrado.");
    }
}
