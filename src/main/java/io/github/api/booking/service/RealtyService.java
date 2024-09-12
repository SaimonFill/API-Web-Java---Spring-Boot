package io.github.api.booking.service;

import io.github.api.booking.domain.Realty;
import io.github.api.booking.domain.User;
import io.github.api.booking.exception.RealtyHasAnAnnouncementException;
import io.github.api.booking.exception.SearchIdInvalidException;
import io.github.api.booking.repository.AnnouncementRepository;
import io.github.api.booking.repository.RealtyRepository;
import io.github.api.booking.request.RegisterRealtyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealtyService {

    private final UserService userService;
    private final RealtyRepository realtyRepository;
    private final AnnouncementRepository announcementRepository;

    public Realty registerRealty(RegisterRealtyRequest registerRealtyRequest) throws Exception {

        final User owner = userService.searchUserById(registerRealtyRequest.getOwnerId());

        final Realty realty = Realty.builder()
                .identification(registerRealtyRequest.getIdentification())
                .realtyType(registerRealtyRequest.getRealtyType())
                .address(registerRealtyRequest.getAddress())
                .owner(owner)
                .characteristics(registerRealtyRequest.getRealtyCharacteristicsList())
                .build();

        realty.setActive(true);
        return realtyRepository.save(realty);
    }

    public Page<Realty> listRealty(Pageable pageable) {
        return realtyRepository.findByActiveTrue(pageable);
    }

    public Page<Realty> searchForPropertyByOwnerId(Pageable pageable, Long ownerId) {
        return realtyRepository.findByActiveTrueAndOwnerId(pageable, ownerId);
    }

    public Realty searchByRealtyId(Long realtyId) throws Exception {
        boolean activeRealty = realtyRepository.existsByActiveTrueAndId(realtyId);

        if (!activeRealty) {
            throw new SearchIdInvalidException("Realty", realtyId);
        }
        return realtyRepository.findByActiveTrueAndId(realtyId);
    }

    public void deleteRealty(Long realtyId) throws Exception {
        boolean RealtyHasAnnouncement = announcementRepository.existsByActiveTrueAndRealtyId(realtyId);

        if (RealtyHasAnnouncement) {
            throw new RealtyHasAnAnnouncementException();
        }

        Realty realty = realtyRepository.findById(realtyId)
                .orElseThrow(() -> new SearchIdInvalidException("Realty", realtyId));

        if (!realty.isActive()) {
            throw new SearchIdInvalidException("Realty", realtyId);
        }

        realty.setActive(false);
        realtyRepository.save(realty);
    }
}
