package io.github.api.booking.repository;

import io.github.api.booking.domain.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Page<Announcement> findByActiveTrue(Pageable pageable);

    Page<Announcement> findByAdvertiserIdAndActiveTrue(Pageable pageable, Long advertiserId);

    boolean existsByActiveTrueAndRealtyId(Long realtyId);

}
