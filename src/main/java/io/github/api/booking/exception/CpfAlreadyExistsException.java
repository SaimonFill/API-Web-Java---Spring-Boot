package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfAlreadyExistsException extends Exception {
    public CpfAlreadyExistsException(String cpf) {
        super(String.format("There is already a resource of type User with CPF with the value '%s'.", cpf));
    }
}