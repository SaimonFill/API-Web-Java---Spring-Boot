package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RealtyHasAnAnnouncementException extends Exception {
    public RealtyHasAnAnnouncementException() {
        super("It is not possible to delete a property that has an ad.");
    }
}
