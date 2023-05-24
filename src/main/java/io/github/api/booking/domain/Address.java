package io.github.api.booking.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Required field not informed. Please inform the field zipCode.")
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "The zip code must be informed in the format 99999-999.")
    private String zipCode;

    @NotBlank(message = "Required field not informed. Please inform the field publicPlace.")
    private String publicPlace;

    @NotBlank(message = "Required field not informed. Please inform the field number.")
    private String number;

    private String complement;

    @NotBlank(message = "Required field not informed. Please inform the field neighborhood.")
    private String neighborhood;

    @NotBlank(message = "Required field not informed. Please inform the field city.")
    private String city;

    @NotBlank(message = "Required field not informed. Please inform the field state.")
    private String state;

}
