package io.github.api.booking.fixture;

import io.github.api.booking.domain.RealtyCharacteristics;

public class CaracteristicaImovelFixture {

    private final RealtyCharacteristics.RealtyCharacteristicsBuilder builder = RealtyCharacteristics.builder();

    public static CaracteristicaImovelFixture get() {
        return new CaracteristicaImovelFixture();
    }

    public RealtyCharacteristics build() {
        return builder.build();
    }

    public CaracteristicaImovelFixture valido() {
        comDescricao();

        return this;
    }

    public void comDescricao() {
        builder.description("Característica Fixture");
    }

}
