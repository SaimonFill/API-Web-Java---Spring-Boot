package io.github.api.reservas.exception;

import io.github.api.reservas.domain.StatusPagamento;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PagamentoInvalidoException extends Exception {
    public PagamentoInvalidoException(String acao, StatusPagamento statusPagamento) {
        super(String.format("Não é possível realizar o %s para esta reserva, pois ela não está no status %s.",acao , statusPagamento));
    }
}
