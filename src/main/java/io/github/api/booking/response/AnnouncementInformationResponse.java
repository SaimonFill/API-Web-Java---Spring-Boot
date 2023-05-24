package io.github.api.booking.response;

import io.github.api.booking.domain.PaymentTypes;
import io.github.api.booking.domain.Realty;
import io.github.api.booking.domain.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementInformationResponse {

    private Long id;
    private Realty realty;
    private User advertiser;
    private List<PaymentTypes> paymentTypesList;
    private String description;

}
