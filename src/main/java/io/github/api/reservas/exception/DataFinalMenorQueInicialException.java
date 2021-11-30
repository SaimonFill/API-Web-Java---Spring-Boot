package io.github.api.reservas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataFinalMenorQueInicialException extends Exception {
    public DataFinalMenorQueInicialException() {
        super("Período inválido! A data final da reserva precisa ser maior do que a data inicial.");
    }
}
