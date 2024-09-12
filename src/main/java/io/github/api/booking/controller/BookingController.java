package io.github.api.booking.controller;

import io.github.api.booking.domain.Booking;
import io.github.api.booking.domain.PaymentTypes;
import io.github.api.booking.request.CreateBookingRequest;
import io.github.api.booking.response.BookingInformationResponse;
import io.github.api.booking.response.BookingResponse;
import io.github.api.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    private final BookingResponse bookingResponse;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingInformationResponse createBooking(@RequestBody @Valid CreateBookingRequest createBookingRequest) throws Exception {
        bookingService.createBooking(createBookingRequest);
        return bookingResponse.bookingResponse(createBookingRequest);
    }

    @GetMapping(path = "/applicants/{applicantsId}")
    public Page<Booking> searchByReservationByRequestingId
            (@PathVariable Long applicantsId,
             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime finalDate,
             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime initDate,
             @PageableDefault(sort = "period", direction = Sort.Direction.DESC) Pageable pageable) {
        return bookingService.searchByReservationByRequestingId(applicantsId, finalDate, initDate, pageable);
    }

    @GetMapping(path = "/announcements/advertisers/{advertisersId}")
    public Page<Booking> searchBookingByAdvertiserId(
            @PathVariable Long advertisersId,
            @PageableDefault(sort = "period", direction = Sort.Direction.DESC) Pageable pageable) {
        return bookingService.searchBookingByAdvertiserId(advertisersId, pageable);
    }

    @PutMapping(path = "{bookingId}/payment")
    public void bookingPayment(@PathVariable Long bookingId, @RequestBody PaymentTypes paymentTypes) throws Exception {
        bookingService.bookingPayment(bookingId, paymentTypes);
    }

    @PutMapping(path = "{bookingId}/payment/cancel")
    public void cancelBooking(@PathVariable Long bookingId) throws Exception {
        bookingService.cancelBooking(bookingId);
    }

    @PutMapping(path = "{bookingId}/payment/reverse")
    public void reverseBooking(@PathVariable Long bookingId) throws Exception {
        bookingService.reverseBooking(bookingId);
    }
}
