package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataOverlayException extends Exception {
    public DataOverlayException() {
        super("This ad is already reserved for the period informed.");
    }
}
