package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SearchIdInvalidException extends Exception {
    public SearchIdInvalidException(String entidade, Long id) {
        super(String.format("No %s with Id with value '%d' was found.", entidade, id));
    }
}