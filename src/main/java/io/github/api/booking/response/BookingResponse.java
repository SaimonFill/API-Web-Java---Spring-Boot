package io.github.api.booking.response;

import io.github.api.booking.domain.Announcement;
import io.github.api.booking.domain.Booking;
import io.github.api.booking.domain.User;
import io.github.api.booking.request.CreateBookingRequest;
import io.github.api.booking.service.AnnouncementService;
import io.github.api.booking.service.BookingService;
import io.github.api.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingResponse {

    private final BookingService bookingService;
    private final UserService userService;
    private final AnnouncementService announcementService;


    public BookingInformationResponse bookingResponse(CreateBookingRequest createBookingRequest) throws Exception {
        final User requesterName = userService.searchUserById(createBookingRequest.getRequesterId());
        final Announcement dadosAnnouncement = announcementService.searchAnnouncementById(createBookingRequest.getAnnouncementId());
        final Booking bookingInformation = bookingService.searchBooking(createBookingRequest);

        return BookingInformationResponse.builder()
                .bookingId(bookingInformation.getId())
                .requester(RequesterInformationResponse.builder()
                        .id(createBookingRequest.getRequesterId())
                        .name(requesterName.getName())
                        .build())
                .peopleQuantity(bookingInformation.getPeopleQuantity())
                .announcement(AnnouncementInformationResponse.builder()
                        .id(dadosAnnouncement.getId())
                        .realty(dadosAnnouncement.getRealty())
                        .advertiser(dadosAnnouncement.getAdvertiser())
                        .paymentTypesList(dadosAnnouncement.getPaymentTypes())
                        .description(dadosAnnouncement.getDescription())
                        .build())
                .period(bookingInformation.getPeriod())
                .payment(bookingInformation.getPayment())
                .build();
    }
}
