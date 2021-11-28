package io.github.cwireset.tcc.exception;

import io.github.cwireset.tcc.domain.FormaPagamento;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.Normalizer;
import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormaPagamentoInvalidaException extends Exception {
    public FormaPagamentoInvalidaException(FormaPagamento formaPagamento, List<FormaPagamento> formasAceitas){
        super(String.format("O anúncio não aceita %s como forma de pagamento. As formas aceitas são %s", formaPagamento, formasAceitas));
    }
}
