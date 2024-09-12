package io.github.api.booking.request;

import io.github.api.booking.domain.Address;
import io.github.api.booking.domain.RealtyCharacteristics;
import io.github.api.booking.domain.RealtyType;
import io.github.api.booking.domain.User;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRealtyRequest {

    @NotBlank(message = "Required field not informed. Please inform the field identification.")
    private String identification;

    @NotNull(message = "Required field not informed. Please inform the field realtyType.")
    private RealtyType realtyType;

    @Valid
    @NotNull(message = "Required field not informed. Please inform the field address.")
    private Address address;

    private User owner;

    private List<RealtyCharacteristics> realtyCharacteristicsList;

    @NotNull(message = "Required field not informed. Please inform the field ownerId.")
    private Long ownerId;

}
