package io.github.api.booking.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.api.booking.domain.Address;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Required field not informed. Please inform the field name.")
    private String name;

    @NotBlank(message = "Required field not informed. Please inform the field email.")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Required field not informed. Please inform the field password.")
    private String password;

    @Pattern(regexp = "[0-9]{11}+", message = "O CPF deve ser informado no formato 99999999999.")
    @NotBlank(message = "Required field not informed. Please inform the field cpf.")
    private String cpf;

    @NotNull(message = "Required field not informed. Please inform the field birthDate.")
    private LocalDate birthDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Valid
    private Address address;

}
