package io.github.api.booking.controller;

import io.github.api.booking.domain.Announcement;
import io.github.api.booking.request.RegisterAnnouncementRequest;
import io.github.api.booking.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<Announcement> registerAnnouncement(@RequestBody @Valid RegisterAnnouncementRequest registerAnnouncementRequest) throws Exception {
        Announcement announcement = announcementService.registerAnnouncement(registerAnnouncementRequest);
        return ResponseEntity.created(URI.create("/announcement")).body(announcement);
    }

    @GetMapping
    public Page<Announcement> listAnnouncements(@PageableDefault(sort = "dayValue", direction = Sort.Direction.ASC) Pageable pageable) {
        return announcementService.listAnnouncements(pageable);
    }

    @GetMapping(path = "/advertiser/{advertiserId}")
    public Page<Announcement> searchAdvertisementByAdvertiserId(
            @PageableDefault(sort = "dayValue", direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable Long advertiserId) {
        return announcementService.searchAdvertisementByAdvertiserId(pageable, advertiserId);
    }

    @DeleteMapping(path = "/{announcementId}")
    public void deleteAnnouncement(@PathVariable Long announcementId) throws Exception {
        announcementService.deleteAnnouncement(announcementId);
    }
}
