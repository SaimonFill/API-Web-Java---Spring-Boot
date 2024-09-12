package io.github.api.booking.exception;

import io.github.api.booking.domain.PaymentStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPaymentException extends Exception {
    public InvalidPaymentException(String acao, PaymentStatus paymentStatus) {
        super(String.format("Não é possível realizar o %s para esta reserva, pois ela não está no status %s.", acao, paymentStatus));
    }
}
