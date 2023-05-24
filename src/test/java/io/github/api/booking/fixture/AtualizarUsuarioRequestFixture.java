package io.github.api.booking.fixture;

import io.github.api.booking.domain.Address;
import io.github.api.booking.request.UpdateUserRequest;

import java.time.LocalDate;

public class AtualizarUsuarioRequestFixture {

    private final UpdateUserRequest.UpdateUserRequestBuilder builder = UpdateUserRequest.builder();

    public static AtualizarUsuarioRequestFixture get() {
        return new AtualizarUsuarioRequestFixture();
    }

    public UpdateUserRequest build() {
        return builder.build();
    }

    public AtualizarUsuarioRequestFixture valido() {
        comNome()
        .comEmail()
        .comSenha()
        .comDataNascimento()
        .comEnderecoValido();

        return this;
    }

    public AtualizarUsuarioRequestFixture comNome() {
        builder.name("Updated Tester Subject");
        return this;
    }

    public AtualizarUsuarioRequestFixture comEmail() {
        comEmail("updatedemail@teste.com");
        return this;
    }

    public AtualizarUsuarioRequestFixture comEmail(String email) {
        builder.email(email);
        return this;
    }

    public AtualizarUsuarioRequestFixture comSenha() {
        builder.password("updateds1e2n3h4a5");
        return this;
    }

    public AtualizarUsuarioRequestFixture comDataNascimento() {
        builder.birthDate(LocalDate.of(1981, 1, 1));
        return this;
    }

    public void comEnderecoValido() {
        comEndereco(EnderecoFixture.get().valido().build());
    }

    public AtualizarUsuarioRequestFixture comEndereco(Address address) {
        builder.address(address);
        return this;
    }

}
