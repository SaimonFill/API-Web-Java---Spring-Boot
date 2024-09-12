package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RealtyAlreadyHasAnAnnouncementException extends Exception {
    public RealtyAlreadyHasAnAnnouncementException(Long realtyId) {
        super(String.format("There is already a resource of the type Announcement with IdImovel with the value '%d'.", realtyId));
    }
}