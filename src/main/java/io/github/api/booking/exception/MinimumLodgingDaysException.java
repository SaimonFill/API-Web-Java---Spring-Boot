package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MinimumLodgingDaysException extends Exception {
    public MinimumLodgingDaysException() {
        super("It is not possible to make a reservation with less than 5 nights for Lodging type properties");
    }
}
