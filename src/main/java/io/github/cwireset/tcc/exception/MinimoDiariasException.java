package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MinimoDiariasException extends Exception {
    public MinimoDiariasException() {
        super("Período inválido! O número mínimo de diárias precisa ser maior ou igual à 1.");
    }
}
