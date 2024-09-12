package io.github.api.booking.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.api.booking.domain.*;
import io.github.api.booking.exception.InvalidPaymentException;
import io.github.api.booking.exception.InvalidPaymentTypeException;
import io.github.api.booking.exception.SearchIdInvalidException;
import io.github.api.booking.repository.BookingRepository;
import io.github.api.booking.request.CreateBookingRequest;
import io.github.api.booking.validator.BookingValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final UserService userService;
    private final AnnouncementService announcementService;
    private final BookingRepository bookingRepository;

    public void createBooking(CreateBookingRequest createBookingRequest) throws Exception {
        final User requester = userService.searchUserById(createBookingRequest.getRequesterId());
        final Announcement announcement = announcementService.searchAnnouncementById(createBookingRequest.getAnnouncementId());

        BookingValidator bookingValidator = new BookingValidator(announcementService, bookingRepository);

        bookingValidator.isAnnouncementActive(createBookingRequest);
        bookingValidator.validateRequester(createBookingRequest);
        bookingValidator.validateDate(createBookingRequest);
        bookingValidator.validatePeriod(createBookingRequest);
        bookingValidator.validateBoolingHotel(createBookingRequest);
        bookingValidator.validateBookingLodging(createBookingRequest);
        bookingValidator.validateDatesOverlay(createBookingRequest);

        final Payment payment = new Payment(
                BigDecimal.valueOf(calculateTotalPrice(createBookingRequest)),
                null,
                PaymentStatus.PENDING
        );

        final Booking booking = Booking.builder()
                .requester(requester)
                .announcement(announcement)
                .period(createBookingRequest.getPeriod())
                .peopleQuantity(createBookingRequest.getPeopleQuantity())
                .bookingDate(LocalDateTime.now())
                .payment(payment)
                .build();

        bookingRepository.save(booking);
    }

    public double calculateTotalPrice(CreateBookingRequest createBookingRequest) throws Exception {
        Integer days = createBookingRequest.getPeriod().getFinalDate().compareTo(createBookingRequest.getPeriod().getInitDate());
        Double daysPrice = announcementService.searchAnnouncementById(createBookingRequest.getAnnouncementId()).getDayValue().doubleValue();

        return days * daysPrice;
    }

    public Page<Booking> searchByReservationByRequestingId
            (Long requesterId,
             @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime finalDate,
             @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime initDate,
             Pageable pageable) {

        if (finalDate != null && initDate != null) {
            return bookingRepository.findByPeriod_InitDateGreaterThanEqualAndPeriod_FinalDateLessThanEqualAndRequester_Id
                    (initDate, finalDate, requesterId, pageable);
        }
        return bookingRepository.findByRequesterId(requesterId, pageable);
    }

    public Page<Booking> searchBookingByAdvertiserId(Long idAnunciante, Pageable pageable) {
        return bookingRepository.findByAnnouncement_Advertiser_Id(idAnunciante, pageable);
    }

    public void bookingPayment(Long bookingId, PaymentTypes paymentTypes) throws Exception {
        boolean paymentType = bookingRepository.existsByIdAndAnnouncement_PaymentTypes(bookingId, paymentTypes);
        final Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new SearchIdInvalidException("Booking", bookingId));

        if (!paymentType) {
            throw new InvalidPaymentTypeException(paymentTypes, booking.getAnnouncement().getPaymentTypes());
        }
        if (!booking.getPayment().getStatus().equals(PaymentStatus.PENDING)) {
            throw new InvalidPaymentException("payment", PaymentStatus.PENDING);
        }

        booking.getPayment().setPaymentChosen(paymentTypes);
        booking.getPayment().setStatus(PaymentStatus.PAID);
        bookingRepository.save(booking);
    }

    public void cancelBooking(Long bookingId) throws Exception {
        final Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new SearchIdInvalidException("Booking", bookingId));

        if (!booking.getPayment().getStatus().equals(PaymentStatus.PENDING)) {
            throw new InvalidPaymentException("cancel", PaymentStatus.PENDING);
        }

        booking.getPayment().setStatus(PaymentStatus.CANCEL);
        bookingRepository.save(booking);
    }

    public void reverseBooking(Long bookingId) throws Exception {
        final Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new SearchIdInvalidException("Booking", bookingId));

        if (!booking.getPayment().getStatus().equals(PaymentStatus.PAID)) {
            throw new InvalidPaymentException("reverse", PaymentStatus.PAID);
        }

        booking.getPayment().setStatus(PaymentStatus.REVERSE);
        booking.getPayment().setPaymentChosen(null);
        bookingRepository.save(booking);
    }

    public Booking searchBooking(CreateBookingRequest createBookingRequest) {
        return bookingRepository.findByPeriod_InitDateEqualsAndPeriod_FinalDateEqualsAndAnnouncement_Id
                (createBookingRequest.getPeriod().getInitDate(), createBookingRequest.getPeriod().getFinalDate(), createBookingRequest.getAnnouncementId());
    }
}
