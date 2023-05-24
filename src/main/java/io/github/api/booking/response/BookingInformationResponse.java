package io.github.api.booking.response;

import io.github.api.booking.domain.Payment;
import io.github.api.booking.domain.Period;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
public class BookingInformationResponse {

    private Long bookingId;
    private RequesterInformationResponse requester;
    private Integer peopleQuantity;
    private AnnouncementInformationResponse announcement;
    private Period period;
    private Payment payment;

}
