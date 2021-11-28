package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Periodo;
import io.github.cwireset.tcc.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
public class CadastrarReservaRequest {

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo idSolicitante.")
    private Long idSolicitante;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo idAnuncio.")
    private Long idAnuncio;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo periodo.")
    private Periodo periodo;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo quantidadePessoas.")
    private Integer quantidadePessoas;

    public CadastrarReservaRequest(Long idSolicitante, Long idAnuncio, Periodo periodo, Integer quantidadePessoas) {
        this.idSolicitante = idSolicitante;
        this.idAnuncio = idAnuncio;
        this.periodo = periodo;
        this.quantidadePessoas = quantidadePessoas;
    }

    public Long getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(Long idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public Long getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Long idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }
}
