package io.github.api.booking.fixture;

import io.github.api.booking.domain.Address;
import io.github.api.booking.domain.User;

import java.time.LocalDate;

public class UsuarioFixture {

    private final User.UserBuilder builder = User.builder();

    public static UsuarioFixture get() {
        return new UsuarioFixture();
    }

    public UsuarioFixture valido() {
        comCpf()
        .comNome()
        .comEmail()
        .comSenha()
        .comDataNascimento()
        .comEnderecoValido();

        return this;
    }

    public UsuarioFixture semCamposObrigatorios() {
        comEnderecoValido();

        return this;
    }

    public UsuarioFixture comCpf() {
        comCpf("11122233344");
        return this;
    }

    public UsuarioFixture comCpf(String cpf) {
        builder.cpf(cpf);
        return this;
    }

    public UsuarioFixture comNome() {
        builder.name("Tester Subject");
        return this;
    }

    public UsuarioFixture comEmail() {
        comEmail("email@teste.com");
        return this;
    }

    public UsuarioFixture comEmail(String email) {
        builder.email(email);
        return this;
    }

    public UsuarioFixture comSenha() {
        builder.password("s1e2n3h4a5");
        return this;
    }

    public UsuarioFixture comDataNascimento() {
        builder.birthDate(LocalDate.of(1980, 1, 1));
        return this;
    }

    public void comEnderecoValido() {
        comEndereco(EnderecoFixture.get().valido().build());
    }

    public UsuarioFixture comEndereco(Address address) {
        builder.address(address);
        return this;
    }

    public User build() {
        return builder.build();
    }

}
