package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FinalDateException extends Exception {
    public FinalDateException() {
        super("Invalid period! The end date of the booking must be greater than the start date.");
    }
}
