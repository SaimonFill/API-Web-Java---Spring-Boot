package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.CaracteristicaImovel;
import io.github.cwireset.tcc.domain.Endereco;
import io.github.cwireset.tcc.domain.TipoImovel;
import io.github.cwireset.tcc.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadastrarImovelRequest {

    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo identificacao.")
    private String identificacao;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo tipoImovel.")
    private TipoImovel tipoImovel;

    @Valid
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo endereco.")
    private Endereco endereco;

    private Usuario proprietario;

    private List<CaracteristicaImovel> caracteristicas;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo idProprietario.")
    private Long idProprietario;

    public CadastrarImovelRequest(String identificacao, TipoImovel tipoImovel, Endereco endereco, List<CaracteristicaImovel> caracteristicas, Long idProprietario) {
        this.identificacao = identificacao;
        this.tipoImovel = tipoImovel;
        this.endereco = endereco;
        this.caracteristicas = caracteristicas;
        this.idProprietario = idProprietario;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }

    public List<CaracteristicaImovel> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaImovel> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Long getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(Long idProprietario) {
        this.idProprietario = idProprietario;
    }
}
