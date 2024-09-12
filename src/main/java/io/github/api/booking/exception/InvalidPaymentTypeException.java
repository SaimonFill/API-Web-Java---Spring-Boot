package io.github.api.booking.exception;

import io.github.api.booking.domain.PaymentTypes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPaymentTypeException extends Exception {
    public InvalidPaymentTypeException(PaymentTypes paymentTypes, List<PaymentTypes> typesList) {
        super(String.format("The ad does not accept %s as a payment method. Accepted forms are %s", paymentTypes, typesList));
    }
}
