package io.github.api.booking.request;

import io.github.api.booking.domain.Period;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequest {

    @NotNull(message = "Required field not informed. Please inform the field requesterId.")
    private Long requesterId;

    @NotNull(message = "Required field not informed. Please inform the field announcementId.")
    private Long announcementId;

    @NotNull(message = "Required field not informed. Please inform the field period.")
    private Period period;

    @NotNull(message = "Required field not informed. Please inform the field peopleQuantity.")
    private Integer peopleQuantity;

}
