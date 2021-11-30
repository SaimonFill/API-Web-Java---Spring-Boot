package io.github.api.reservas.request;

import io.github.api.reservas.domain.FormaPagamento;
import io.github.api.reservas.domain.Imovel;
import io.github.api.reservas.domain.TipoAnuncio;
import io.github.api.reservas.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarAnuncioRequest {

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo idImovel.")
    private Long idImovel;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo idAnunciante.")
    private Long idAnunciante;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo tipoAnuncio.")
    private TipoAnuncio tipoAnuncio;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo valorDiaria.")
    private BigDecimal valorDiaria;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo formasAceitas.")
    private List<FormaPagamento> formasAceitas;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo descricao.")
    private String descricao;

    private Imovel imovel;

    private Usuario anunciante;

    public CadastrarAnuncioRequest(Long idImovel, Long idAnunciante, TipoAnuncio tipoAnuncio, BigDecimal valorDiaria, List<FormaPagamento> formasAceitas, String descricao) {
        this.idImovel = idImovel;
        this.idAnunciante = idAnunciante;
        this.tipoAnuncio = tipoAnuncio;
        this.valorDiaria = valorDiaria;
        this.formasAceitas = formasAceitas;
        this.descricao = descricao;
    }

    public Long getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Long idImovel) {
        this.idImovel = idImovel;
    }

    public Long getIdAnunciante() {
        return idAnunciante;
    }

    public void setIdAnunciante(Long idAnunciante) {
        this.idAnunciante = idAnunciante;
    }

    public TipoAnuncio getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(TipoAnuncio tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public List<FormaPagamento> getFormasAceitas() {
        return formasAceitas;
    }

    public void setFormasAceitas(List<FormaPagamento> formasAceitas) {
        this.formasAceitas = formasAceitas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Usuario getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Usuario anunciante) {
        this.anunciante = anunciante;
    }
}
