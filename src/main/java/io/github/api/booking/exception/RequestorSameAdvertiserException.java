package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestorSameAdvertiserException extends Exception {
    public RequestorSameAdvertiserException() {
        super("The requester of a reservation cannot be the advertiser himself.");
    }
}
