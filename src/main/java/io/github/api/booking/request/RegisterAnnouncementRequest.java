package io.github.api.booking.request;

import io.github.api.booking.domain.AnnouncementType;
import io.github.api.booking.domain.PaymentTypes;
import io.github.api.booking.domain.Realty;
import io.github.api.booking.domain.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAnnouncementRequest {

    @NotNull(message = "Required field not informed. Please inform the field realtyId.")
    private Long realtyId;

    @NotNull(message = "Required field not informed. Please inform the field advertiserId.")
    private Long advertiserId;

    @NotNull(message = "Required field not informed. Please inform the field announcementType.")
    private AnnouncementType announcementType;

    @NotNull(message = "Required field not informed. Please inform the field dayValue.")
    private BigDecimal dayValue;

    @NotNull(message = "Required field not informed. Please inform the field paymentTypes.")
    private List<PaymentTypes> paymentTypes;

    @NotBlank(message = "Required field not informed. Please inform the field description.")
    private String description;

    private Realty realty;

    private User advertiser;

}
