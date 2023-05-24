package io.github.api.booking.fixture;

import io.github.api.booking.domain.Address;
import io.github.api.booking.request.RegisterRealtyRequest;
import io.github.api.booking.domain.RealtyType;

import java.util.Arrays;

public class CadastrarImovelRequestFixture {

    private final RegisterRealtyRequest.RegisterRealtyRequestBuilder builder = RegisterRealtyRequest.builder();

    public static CadastrarImovelRequestFixture get() {
        return new CadastrarImovelRequestFixture();
    }

    public RegisterRealtyRequest build() {
        return builder.build();
    }

    public CadastrarImovelRequestFixture semCamposObrigatorios() {
        return comCaracteristicas();
    }

    public CadastrarImovelRequestFixture valido() {
        return comTipoImovel()
                .comIdentificacao()
                .comIdProprietario()
                .comCaracteristicas()
                .comEnderecoValido();
    }

    public CadastrarImovelRequestFixture comTipoImovel() {
        return comTipoImovel(RealtyType.HOUSE);
    }

    public CadastrarImovelRequestFixture comTipoImovel(RealtyType realtyType) {
        builder.realtyType(realtyType);
        return this;
    }

    public CadastrarImovelRequestFixture comEndereco(Address address) {
        builder.address(address);
        return this;
    }

    public CadastrarImovelRequestFixture comEnderecoValido() {
        return comEndereco(EnderecoFixture.get().valido().build());
    }

    public CadastrarImovelRequestFixture comIdentificacao() {
        builder.identification("Casa da Fixture");
        return this;
    }

    public CadastrarImovelRequestFixture comIdProprietario() {
        return comIdProprietario(1L);
    }

    public CadastrarImovelRequestFixture comIdProprietario(Long idProprietario) {
        builder.ownerId(idProprietario);
        return this;
    }

    public CadastrarImovelRequestFixture comCaracteristicas() {
        builder.realtyCharacteristicsList(Arrays.asList(CaracteristicaImovelFixture.get().valido().build(), CaracteristicaImovelFixture.get().valido().build()));
        return this;
    }

}
