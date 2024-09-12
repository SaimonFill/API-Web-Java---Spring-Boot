package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SearchInvalidCpfException extends Exception {
    public SearchInvalidCpfException(String cpf) {
        super(String.format("No User with CPF with value '%s' was found.", cpf));
    }
}