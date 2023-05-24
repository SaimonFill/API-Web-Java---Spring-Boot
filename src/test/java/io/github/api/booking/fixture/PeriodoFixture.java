package io.github.api.booking.fixture;

import io.github.api.booking.domain.Period;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PeriodoFixture {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final Period.PeriodBuilder builder = Period.builder();

    public static PeriodoFixture get() {
        return new PeriodoFixture();
    }

    public Period build() {
        return builder.build();
    }

    public PeriodoFixture valido(){
        comDataHoraInicial()
            .comDataHoraFinal();

        return this;
    }

    public PeriodoFixture comDataHoraInicial(){
        builder.initDate(LocalDateTime.parse(LocalDateTime.now().format(formatter)));
        return this;
    }

    public void comDataHoraFinal(){
        builder.finalDate(LocalDateTime.parse(LocalDateTime.now().plusDays(2).format(formatter)));
    }

    public PeriodoFixture comDataHoraFinal(LocalDateTime dataHoraFinal){
        builder.finalDate(dataHoraFinal);
        return this;
    }

    public PeriodoFixture comDataHoraInicial(LocalDateTime dataHoraInicial){
        builder.initDate(dataHoraInicial);
        return this;
    }

}
