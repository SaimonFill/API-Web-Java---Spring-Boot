package io.github.api.booking.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.api.booking.domain.Address;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotBlank(message = "Required field not informed. Please inform the field name.")
    private String name;

    @NotBlank(message = "Required field not informed. Please inform the field email.")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Required field not informed. Please inform the field password.")
    private String password;

    @NotNull(message = "Required field not informed. Please inform the field birthDate.")
    private LocalDate birthDate;

    @Valid
    private Address address;

}
