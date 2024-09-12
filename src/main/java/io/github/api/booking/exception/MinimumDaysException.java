package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MinimumDaysException extends Exception {
    public MinimumDaysException() {
        super("Invalid period! The minimum number of nights must be greater than or equal to 1.");
    }
}
