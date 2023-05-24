package io.github.api.booking.fixture;

import io.github.api.booking.domain.Address;

public class EnderecoFixture {

    private final Address.AddressBuilder builder = Address.builder();

    public static EnderecoFixture get() {
        return new EnderecoFixture();
    }

    public Address build() {
        return builder.build();
    }

    public EnderecoFixture valido() {
        comCep()
        .comCidade()
        .comEstado()
        .comBairro()
        .comLogradouro()
        .comNumero()
        .comComplemento();

        return this;
    }

    public EnderecoFixture comCep() {
        comCep("96015-180");
        return this;
    }

    public EnderecoFixture comCep(String cep) {
        builder.zipCode(cep);
        return this;
    }

    public EnderecoFixture comCidade() {
        builder.city("Cidade Fixture");
        return this;
    }

    public EnderecoFixture comEstado() {
        builder.state("FF");
        return this;
    }

    public EnderecoFixture comBairro() {
        builder.neighborhood("Bairro Fixture");
        return this;
    }

    public EnderecoFixture comLogradouro() {
        builder.publicPlace("Rua dos Bobos");
        return this;
    }

    public EnderecoFixture comNumero() {
        builder.number("0");
        return this;
    }

    public void comComplemento() {
        builder.complement("Complemento Fixture");
    }

}
