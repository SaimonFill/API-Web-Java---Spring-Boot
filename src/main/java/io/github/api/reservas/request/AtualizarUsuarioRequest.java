package io.github.api.reservas.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.api.reservas.domain.Endereco;
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
public class AtualizarUsuarioRequest {

    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo nome.")
    private String nome;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo email.")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo senha.")
    private String senha;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo dataNascimento.")
    private LocalDate dataNascimento;

    @Valid
    private Endereco endereco;

}
