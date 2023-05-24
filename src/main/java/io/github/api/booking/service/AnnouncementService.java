package io.github.api.booking.service;

import io.github.api.booking.domain.Announcement;
import io.github.api.booking.domain.Realty;
import io.github.api.booking.domain.User;
import io.github.api.booking.exception.RealtyAlreadyHasAnAnnouncementException;
import io.github.api.booking.exception.SearchIdInvalidException;
import io.github.api.booking.repository.AnnouncementRepository;
import io.github.api.booking.request.RegisterAnnouncementRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final UserService userService;
    private final RealtyService realtyService;
    private final AnnouncementRepository announcementRepository;

    public Announcement registerAnnouncement(RegisterAnnouncementRequest registerAnnouncementRequest) throws Exception {
        final User advertiser = userService.searchUserById(registerAnnouncementRequest.getAdvertiserId());
        final Realty realty = realtyService.searchByRealtyId(registerAnnouncementRequest.getRealtyId());
        final Long realtyId = registerAnnouncementRequest.getRealtyId();

        validateRealtyWithAnnouncement(realtyId);

        Announcement announcement = Announcement.builder()
                .announcementType(registerAnnouncementRequest.getAnnouncementType())
                .realty(realty)
                .advertiser(advertiser)
                .dayValue(registerAnnouncementRequest.getDayValue())
                .paymentTypes(registerAnnouncementRequest.getPaymentTypes())
                .description(registerAnnouncementRequest.getDescription())
                .build();

        announcement.setActive(true);
        return announcementRepository.save(announcement);
    }

    public Page<Announcement> listAnnouncements(Pageable pageable) {
        return announcementRepository.findByActiveTrue(pageable);
    }

    public Page<Announcement> searchAdvertisementByAdvertiserId(Pageable pageable, Long idAnunciante) {
        return announcementRepository.findByAdvertiserIdAndActiveTrue(pageable, idAnunciante);
    }

    public void validateRealtyWithAnnouncement(Long realtyId) throws Exception {
        boolean realtyHasAnnouncement = announcementRepository.existsByActiveTrueAndRealtyId(realtyId);

        if (realtyHasAnnouncement) {
            throw new RealtyAlreadyHasAnAnnouncementException(realtyId);
        }
    }

    public void deleteAnnouncement(Long idAnuncio) throws Exception {

        Announcement announcement = announcementRepository.findById(idAnuncio)
                .orElseThrow(() -> new SearchIdInvalidException("Announcement", idAnuncio));
        announcement.setActive(false);
        announcementRepository.save(announcement);
    }

    public Announcement searchAnnouncementById(Long idAnuncio) throws Exception {
        return announcementRepository.findById(idAnuncio)
                .orElseThrow(() -> new SearchIdInvalidException("Announcement", idAnuncio));
    }
}
