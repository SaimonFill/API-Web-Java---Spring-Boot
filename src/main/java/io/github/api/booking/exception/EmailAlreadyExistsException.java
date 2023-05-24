package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String email) {
        super(String.format("There is already a resource of type User with E-Mail with the value '%s'.", email));
    }
}
