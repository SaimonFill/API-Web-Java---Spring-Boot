package io.github.api.booking.fixture;

import io.github.api.booking.request.RegisterAnnouncementRequest;
import io.github.api.booking.domain.PaymentTypes;
import io.github.api.booking.domain.AnnouncementType;

import java.math.BigDecimal;
import java.util.Arrays;


public class CadastrarAnuncioRequestFixture {

    private static final long ID_ANUNCIANTE_EXISTENTE = 1L;
    private static final long ID_IMOVEL_SEM_ANUNCIO = 11L;

    private final RegisterAnnouncementRequest.RegisterAnnouncementRequestBuilder builder = RegisterAnnouncementRequest.builder();

    public static CadastrarAnuncioRequestFixture get() {
        return new CadastrarAnuncioRequestFixture();
    }

    public RegisterAnnouncementRequest build() {
        return builder.build();
    }

    public CadastrarAnuncioRequestFixture valido() {
        comIdImovel()
            .comIdAnunciante()
            .comValorDiaria()
            .comFormasDePagamento()
            .comDescricao()
            .comTipoAnuncio();

            return this;
    }

    public CadastrarAnuncioRequestFixture comIdImovel(){
        builder.realtyId(ID_IMOVEL_SEM_ANUNCIO);
        return this;
    }

    public CadastrarAnuncioRequestFixture comIdAnunciante(){
        builder.advertiserId(ID_ANUNCIANTE_EXISTENTE);
        return this;
    }

    public CadastrarAnuncioRequestFixture comValorDiaria(){
        builder.dayValue(BigDecimal.valueOf(200));
        return this;
    }

    public CadastrarAnuncioRequestFixture comFormasDePagamento(){
        builder.paymentTypes(Arrays.asList(PaymentTypes.PIX, PaymentTypes.SPECIE));
        return this;
    }

    public CadastrarAnuncioRequestFixture comDescricao(){
        builder.description("Casa perfeita para passar a virada do ano");
        return this;
    }

    public void comTipoAnuncio(){
        builder.announcementType(AnnouncementType.COMPLETE);
    }

    public CadastrarAnuncioRequestFixture comIdAnunciante(long idAnunciante){
        builder.advertiserId(idAnunciante);
        return this;
    }

    public CadastrarAnuncioRequestFixture comIdImovel(long idImovel){
        builder.realtyId(idImovel);
        return this;
    }

}
