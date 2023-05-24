package io.github.api.booking.repository;

import io.github.api.booking.domain.Booking;
import io.github.api.booking.domain.PaymentStatus;
import io.github.api.booking.domain.PaymentTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByPaymentStatusEqualsOrPaymentStatusEquals(PaymentStatus cancel, PaymentStatus reverse);

    Page<Booking> findByRequesterId(Long requesterId, Pageable pageable);

    Page<Booking> findByAnnouncement_Advertiser_Id(Long advertiserId, Pageable pageable);

    boolean existsByIdAndAnnouncement_PaymentTypes(Long id, List<PaymentTypes> announcement_paymentTypes);

    boolean existsByPeriod_InitDateLessThanAndAnnouncement_Id(LocalDateTime finalDate, Long announcementId);

    Booking findByPeriod_InitDateEqualsAndPeriod_FinalDateEqualsAndAnnouncement_Id(LocalDateTime initDate, LocalDateTime finalDate, Long announcementId);

    boolean existsByPeriod_FinalDateGreaterThanAndAnnouncement_Id(LocalDateTime initDate, Long announcementId);

    Page<Booking> findByPeriod_InitDateGreaterThanEqualAndPeriod_FinalDateLessThanEqualAndRequester_Id(LocalDateTime initDate, LocalDateTime finalDate, Long requesterId, Pageable pageable);

}
