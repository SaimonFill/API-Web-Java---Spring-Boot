package io.github.cwireset.tcc.exception;

import io.github.cwireset.tcc.domain.StatusPagamento;

public class PagamentoInvalidoException extends Exception {
    public PagamentoInvalidoException(String acao, StatusPagamento statusPagamento) {
        super(String.format("Não é possível realizar o %s para esta reserva, pois ela não está no status %s.",acao , statusPagamento));
    }
}
