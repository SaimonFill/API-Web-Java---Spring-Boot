package io.github.api.booking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Pattern(regexp = "[0-9]{11}+", message = "The CPF must be informed in the format 99999999999.")
    private String cpf;

    private LocalDate birthDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Valid
    private Address address;

}
