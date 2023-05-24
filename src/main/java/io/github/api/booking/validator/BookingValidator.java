package io.github.api.booking.validator;

import io.github.api.booking.domain.Announcement;
import io.github.api.booking.domain.PaymentStatus;
import io.github.api.booking.domain.RealtyType;
import io.github.api.booking.exception.*;
import io.github.api.booking.repository.BookingRepository;
import io.github.api.booking.request.CreateBookingRequest;
import io.github.api.booking.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookingValidator {

    private final AnnouncementService announcementService;
    private final BookingRepository bookingRepository;

    public void validateDate(CreateBookingRequest createBookingRequest) {
        LocalDateTime initDate = createBookingRequest.getPeriod().getInitDate();
        LocalDateTime finalDate = createBookingRequest.getPeriod().getFinalDate();

        if (initDate.getHour() != 14 || initDate.getMinute() != 0) {
            createBookingRequest.getPeriod().setInitDate(LocalDateTime.of(
                    initDate.getYear(),
                    initDate.getMonth(),
                    initDate.getDayOfMonth(),
                    14,
                    0
            ));
        }
        if (finalDate.getHour() != 12 || finalDate.getMinute() != 0) {
            createBookingRequest.getPeriod().setFinalDate(LocalDateTime.of(
                    finalDate.getYear(),
                    finalDate.getMonth(),
                    finalDate.getDayOfMonth(),
                    12,
                    0
            ));
        }
    }

    public void validatePeriod(CreateBookingRequest createBookingRequest) throws Exception {
        int differencePeriod = createBookingRequest.getPeriod().getFinalDate().compareTo(createBookingRequest.getPeriod().getInitDate());
        LocalDateTime initDate = createBookingRequest.getPeriod().getInitDate();
        LocalDateTime finalDate = createBookingRequest.getPeriod().getFinalDate();

        if (finalDate.getDayOfMonth() == initDate.getDayOfMonth()) {
            throw new MinimumDaysException();
        }
        if (differencePeriod < 0) {
            throw new FinalDateException();
        }
    }

    public void validateRequester(CreateBookingRequest createBookingRequest) throws Exception {
        Long requesterId = createBookingRequest.getRequesterId();
        final Announcement announcement = announcementService.searchAnnouncementById(createBookingRequest.getAnnouncementId());
        Long advertiserId = announcement.getAdvertiser().getId();

        if (Objects.equals(advertiserId, requesterId)) {
            throw new RequestorSameAdvertiserException();
        }
    }

    public void validateBoolingHotel(CreateBookingRequest createBookingRequest) throws Exception {
        final Announcement realtyType = announcementService.searchAnnouncementById(createBookingRequest.getAnnouncementId());
        boolean isHotel = realtyType.getRealty().getRealtyType().equals(RealtyType.HOTEL);
        Integer peopleQuantity = createBookingRequest.getPeopleQuantity();

        if (isHotel && peopleQuantity < 2) {
            throw new HotelMinimumPeopleException();
        }
    }

    public void validateBookingLodging(CreateBookingRequest createBookingRequest) throws Exception {
        final Announcement realtyType = announcementService.searchAnnouncementById(createBookingRequest.getAnnouncementId());
        boolean isLodging = realtyType.getRealty().getRealtyType().equals(RealtyType.LODGING);
        int days = createBookingRequest.getPeriod().getFinalDate().compareTo(createBookingRequest.getPeriod().getInitDate());

        if (isLodging && days < 5) {
            throw new MinimumLodgingDaysException();
        }
    }

    public void validateDatesOverlay(CreateBookingRequest createBookingRequest) throws Exception {
        boolean activeBookings = bookingRepository.existsByPaymentStatusEqualsOrPaymentStatusEquals
                (PaymentStatus.PAID, PaymentStatus.PENDING);

        boolean overlayInitDate = bookingRepository.existsByPeriod_FinalDateGreaterThanAndAnnouncement_Id
                (createBookingRequest.getPeriod().getInitDate(), createBookingRequest.getAnnouncementId());

        boolean overlayFinalDate = bookingRepository.existsByPeriod_InitDateLessThanAndAnnouncement_Id
                (createBookingRequest.getPeriod().getFinalDate(), createBookingRequest.getAnnouncementId());

        if (activeBookings) {
            if (overlayInitDate && overlayFinalDate) {
                throw new DataOverlayException();
            }
        }
    }

    public void isAnnouncementActive(CreateBookingRequest createBookingRequest) throws Exception {
        final Announcement announcement = announcementService.searchAnnouncementById(createBookingRequest.getAnnouncementId());

        if (!announcement.isActive()) {
            throw new SearchIdInvalidException("Announcement", createBookingRequest.getAnnouncementId());
        }
    }
}
