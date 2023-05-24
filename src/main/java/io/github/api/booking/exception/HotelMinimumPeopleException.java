package io.github.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HotelMinimumPeopleException extends Exception {
    public HotelMinimumPeopleException() {
        super("It is not possible to make a reservation with less than 2 people for Hotel type properties");
    }
}
