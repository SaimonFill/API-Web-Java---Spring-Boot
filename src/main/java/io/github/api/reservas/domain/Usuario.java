package io.github.api.reservas.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo nome.")
    private String nome;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo email.")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo senha.")
    private String senha;

    @Pattern(regexp = "[0-9]{11}+", message = "O CPF deve ser informado no formato 99999999999.")
    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo cpf.")
    private String cpf;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo dataNascimento.")
    private LocalDate dataNascimento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    @Valid
    private Endereco endereco;

    private String avatar;
}
