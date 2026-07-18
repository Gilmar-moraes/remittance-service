package br.inter.dti.gmoraes.remittance.domain.exception;

public class DocumentoObrigatorioException extends RegraNegocioException {

    public DocumentoObrigatorioException(String documento) {
        super(documento + " é obrigatório para o tipo de usuário informado.");
    }
}
