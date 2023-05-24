package io.github.api.booking.controller;

import io.github.api.booking.domain.Realty;
import io.github.api.booking.request.RegisterRealtyRequest;
import io.github.api.booking.service.RealtyService;
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
@RequestMapping("/realty")
public class RealtyController {

    private final RealtyService realtyService;

    @PostMapping
    public ResponseEntity<Realty> registerRealty(@RequestBody @Valid RegisterRealtyRequest registerRealtyRequest) throws Exception {
        Realty realty = realtyService.registerRealty(registerRealtyRequest);
        return ResponseEntity.created(URI.create("/realty")).body(realty);
    }

    @GetMapping
    public Page<Realty> listRealty(@PageableDefault(sort = "identification", direction = Sort.Direction.ASC) Pageable pageable) {
        return realtyService.listRealty(pageable);
    }

    @GetMapping(path = "/owner/{ownerId}")
    public Page<Realty> searchForPropertyByOwnerId(
            @PageableDefault(sort = "identification", direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable Long ownerId) {
        return realtyService.searchForPropertyByOwnerId(pageable, ownerId);
    }

    @GetMapping(path = "/{realtyId}")
    public Realty searchByRealtyId(@PathVariable Long realtyId) throws Exception {
        return realtyService.searchByRealtyId(realtyId);
    }

    @DeleteMapping(path = "/{realtyId}")
    public void deleteRealty(@PathVariable Long realtyId) throws Exception {
        realtyService.deleteRealty(realtyId);
    }
}
