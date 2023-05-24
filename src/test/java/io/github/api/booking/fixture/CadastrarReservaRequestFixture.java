package io.github.api.booking.fixture;

import io.github.api.booking.request.CreateBookingRequest;
import io.github.api.booking.domain.Period;

import java.time.LocalDateTime;

public class CadastrarReservaRequestFixture {

    private static final long ID_SOLICITANTE_VALIDO = 6;
    private static final long ID_ANUNCIO_VALIDO = 8;

    private final CreateBookingRequest.CreateBookingRequestBuilder builder = CreateBookingRequest.builder();

    public static CadastrarReservaRequestFixture get() {
        return new CadastrarReservaRequestFixture();
    }

    public CreateBookingRequest build() {
        return builder.build();
    }

    public CadastrarReservaRequestFixture valido(){
        comIdAnuncio()
        .comIdSolicitante()
        .comPeriodo()
        .comQuantidadePessoas();

        return this;
    }

    public CadastrarReservaRequestFixture comIdSolicitante() {
        builder.requesterId(ID_SOLICITANTE_VALIDO);
        return this;
    }

    public CadastrarReservaRequestFixture comIdAnuncio() {
        builder.announcementId(ID_ANUNCIO_VALIDO);
        return this;
    }

    public void comQuantidadePessoas() {
        builder.peopleQuantity(4);
    }

    public CadastrarReservaRequestFixture comPeriodo(){
        builder.period(PeriodoFixture.get().valido().build());
        return this;
    }

    public CadastrarReservaRequestFixture comPeriodo(Period period){
        builder.period(period);
        return this;
    }

    public CadastrarReservaRequestFixture comDataHoraFinal(LocalDateTime data) {
        Period period = PeriodoFixture.get().valido().comDataHoraFinal(data).build();
        builder.period(period);

        return this;
    }

    public CadastrarReservaRequestFixture comIdAnunciante(long idAnuncio) {
        builder.announcementId(idAnuncio);

        return this;
    }

    public CadastrarReservaRequestFixture comIdSolicitante(long idSolicitante) {
        builder.requesterId(idSolicitante);

        return this;
    }

    public CadastrarReservaRequestFixture comQuantidadePessoas(int quantidadePessoas) {
        builder.peopleQuantity(quantidadePessoas);

        return this;
    }
}
