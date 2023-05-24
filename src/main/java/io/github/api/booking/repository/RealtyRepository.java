package io.github.api.booking.repository;

import io.github.api.booking.domain.Realty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealtyRepository extends JpaRepository<Realty, Long> {

    Realty findByActiveTrueAndId(Long id);

    boolean existsByActiveTrueAndId(Long realtyId);

    Page<Realty> findByActiveTrueAndOwnerId(Pageable pageable, Long ownerId);

    Page<Realty> findByActiveTrue(Pageable pageable);
}
