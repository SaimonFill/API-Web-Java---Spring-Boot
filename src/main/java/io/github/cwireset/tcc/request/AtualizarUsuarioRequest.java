package io.github.cwireset.tcc.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cwireset.tcc.domain.Endereco;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
public class AtualizarUsuarioRequest {

    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo nome.")
    private String nome;

    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo email.")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Campo obrigatório não informado. Favor informar o campo senha.")
    private String senha;

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo dataNascimento.")
    private LocalDate dataNascimento;

    @Valid
    private Endereco endereco;

    public AtualizarUsuarioRequest(String nome, String email, String senha, LocalDate dataNascimento, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
