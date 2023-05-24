package io.github.api.booking.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    private BigDecimal totalValue;

    @Enumerated(EnumType.STRING)
    private PaymentTypes paymentChosen;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
