package io.github.api.reservas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConsultaCpfInvalidoException extends Exception {
    public ConsultaCpfInvalidoException(String cpf) {
        super(String.format("Nenhum(a) Usuario com CPF com o valor '%s' foi encontrado.", cpf));
    }
}