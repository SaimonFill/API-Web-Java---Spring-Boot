package io.github.api.booking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.api.booking.domain.Address;
import io.github.api.booking.domain.User;
import io.github.api.booking.fixture.AtualizarUsuarioRequestFixture;
import io.github.api.booking.fixture.EnderecoFixture;
import io.github.api.booking.fixture.UsuarioFixture;
import io.github.api.booking.request.UpdateUserRequest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerIntegrationTest {

    private static final String BASE_PATH = "/usuarios";
    public static final int ID_USUARIO_CRIADO = 16; // Existem 15 no import.sql, logo o próximo a ser criado é o 16

    @LocalServerPort
    int port;

    @BeforeAll
    public void setUpRestAssuredFilters() {
//        RestAssured.replaceFiltersWith(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.replaceFiltersWith(new AllureRestAssured());
    }

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    private String getObjectAsJson(Object object) {
        try {
            ObjectMapper mapper = JsonMapper.builder()
                    .configure(MapperFeature.USE_ANNOTATIONS, false)
                    .findAndAddModules()
                    .build();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    // 1.2. Listar usuários

    @Test
    @Order(1)
    void deveListarUsuariosComPaginacaoComSucesso() {
        get(BASE_PATH)
            .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body(containsString("content"))
                .body("totalElements", equalTo(15));
    }

    @Test
    @Order(2)
    void deveListarUsuariosComPaginacaoPorOrdemAlfabeticaComSucesso_Desafio() {
        get(BASE_PATH)
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body(containsString("content"))
            .body("content[0].nome", equalTo("Alexandre"))
            .body("content[1].nome", equalTo("Anunciante"))
            .body("content[2].nome", equalTo("Bruno"))
            .body("content[3].nome", equalTo("Leonardo"));
    }

    @Test
    @Order(3)
    void deveListarUsuariosComPaginacaoSemSenha() {
        get(BASE_PATH)
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body(containsString("content"))
            .body("content[0].senha", nullValue())
            .body("content[1].senha", nullValue())
            .body("content[2].senha", nullValue())
            .body("content[3].senha", nullValue());
    }

    // 1.1. Cadastro de Usuário

    @Test
    @Order(4)
    void deveRetornarBadRequestAoTentarCriarUsuarioSemCamposObrigatorios() {
        User user = UsuarioFixture.get()
                .semCamposObrigatorios()
                .build();

        String usuarioAsJson = getObjectAsJson(user);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioAsJson)
        .when()
            .post(BASE_PATH)
        .then()
            .assertThat()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("message", equalTo("Validation failed for object='usuario'. Error count: 5"));
    }

    @Test
    @Order(5)
    void deveRetornarBadRequestAoTentarCriarUsuarioComMesmoEmail() {
        User user = UsuarioFixture.get()
                .valido()
                .comEmail("usuario1@teste.com") // E-Mail consta no import.sql
                .build();

        String usuarioAsJson = getObjectAsJson(user);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioAsJson)
        .when()
            .post(BASE_PATH)
        .then()
            .assertThat()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("message", equalTo("Já existe um recurso do tipo Usuario com E-Mail com o valor 'usuario1@teste.com'."));
    }

    @Test
    @Order(6)
    void deveRetornarBadRequestAoTentarCriarUsuarioComMesmoCpf() {
        User user = UsuarioFixture.get()
                .valido()
                .comCpf("48044815759") // CPF consta no import.sql
                .build();

        String usuarioAsJson = getObjectAsJson(user);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioAsJson)
        .when()
            .post(BASE_PATH)
        .then()
            .assertThat()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("message", equalTo("Já existe um recurso do tipo Usuario com CPF com o valor '48044815759'."));
    }

    @Test
    @Order(7)
    void deveRetornarBadRequestAoTentarCriarUsuarioComCpfEmFormatoInvalido() {
        User user = UsuarioFixture.get()
                .valido()
                .comCpf("480.448.157-59")
                .build();

        String usuarioAsJson = getObjectAsJson(user);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioAsJson)
        .when()
            .post(BASE_PATH)
        .then()
            .assertThat()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("errors[0].defaultMessage", equalTo("O CPF deve ser informado no formato 99999999999."));
    }

    @Test
    @Order(8)
    void deveRetornarBadRequestAoTentarCriarUsuarioComCepEmFormatoInvalido() {
        Address addressComCepInvalido = EnderecoFixture.get()
                .valido()
                .comCep("99999999")
                .build();
        User user = UsuarioFixture.get()
                .valido()
                .comEndereco(addressComCepInvalido)
                .build();

        String usuarioAsJson = getObjectAsJson(user);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioAsJson)
        .when()
            .post(BASE_PATH)
        .then()
            .assertThat()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("errors[0].defaultMessage", equalTo("O CEP deve ser informado no formato 99999-999."));
    }

    @Test
    @Order(9)
    void deveCriarUsuarioComSucesso() {
        User user = UsuarioFixture.get().valido().build();

        String usuarioAsJson = getObjectAsJson(user);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioAsJson)
        .when()
            .post(BASE_PATH)
        .then()
            .assertThat()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", equalTo(ID_USUARIO_CRIADO))
            .body("nome", equalTo(user.getName()))
            .body("cpf", equalTo(user.getCpf()))
            .body("email", equalTo(user.getEmail()))
            .body("senha", nullValue())
            .body("dataNascimento", equalTo(user.getBirthDate().toString()));
    }

    // 1.3. Buscar um usuário por id

    @Test
    @Order(10)
    void deveBuscarUmUsuarioPorIdComSucesso() {
        User user = UsuarioFixture.get().valido().build();

        given()
        .when()
            .get(BASE_PATH + "/{idUsuario}", ID_USUARIO_CRIADO)
        .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body("id", equalTo(ID_USUARIO_CRIADO))
            .body("nome", equalTo(user.getName()))
            .body("cpf", equalTo(user.getCpf()))
            .body("email", equalTo(user.getEmail()))
            .body("senha", nullValue())
            .body("dataNascimento", equalTo(user.getBirthDate().toString()));
    }

    @Test
    @Order(11)
    void deveRetornarNotFoundAoBuscarUsuarioPorIdQueNaoExiste() {
        given()
        .when()
            .get(BASE_PATH + "/{idUsuario}", 999)
        .then()
            .assertThat()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body("message", equalTo("Nenhum(a) Usuario com Id com o valor '999' foi encontrado."));
    }

    // 1.4. Buscar um usuário por cpf

    @Test
    @Order(12)
    void deveBuscarUmUsuarioPorCpfComSucesso() {
        User user = UsuarioFixture.get().valido().build();

        given()
        .when()
            .get(BASE_PATH + "/cpf/{cpf}", user.getCpf())
        .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body("id", equalTo(ID_USUARIO_CRIADO))
            .body("nome", equalTo(user.getName()))
            .body("cpf", equalTo(user.getCpf()))
            .body("email", equalTo(user.getEmail()))
            .body("senha", nullValue())
            .body("dataNascimento", equalTo(user.getBirthDate().toString()));
    }

    @Test
    @Order(13)
    void deveRetornarNotFoundAoBuscarUsuarioPorCpfQueNaoExiste() {
        given()
        .when()
            .get(BASE_PATH + "/cpf/99988877766")
        .then()
            .assertThat()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body("message", equalTo("Nenhum(a) Usuario com CPF com o valor '99988877766' foi encontrado."));
    }

    // 1.5. Alterar um usuário

    @Test
    @Order(14)
    void deveRetornarBadRequestAoTentarAlterarUsuarioSemCamposObrigatorios() {
        UpdateUserRequest request = AtualizarUsuarioRequestFixture.get().build();

        String json = getObjectAsJson(request);

        given()
            .contentType(ContentType.JSON)
            .body(json)
        .when()
            .put(BASE_PATH + "/{idUsuario}", ID_USUARIO_CRIADO)
        .then()
            .assertThat()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("message", equalTo("Validation failed for object='atualizarUsuarioRequest'. Error count: 4"));
    }

    @Test
    @Order(15)
    void deveRetornarBadRequestAoTentarAlterarUsuarioComMesmoEmail() {
        UpdateUserRequest request = AtualizarUsuarioRequestFixture.get()
                .valido()
                .comEmail("usuario1@teste.com") // E-Mail consta no import.sql
                .build();

        String json = getObjectAsJson(request);

        given()
            .contentType(ContentType.JSON)
            .body(json)
        .when()
            .put(BASE_PATH + "/{idUsuario}", ID_USUARIO_CRIADO)
        .then()
            .assertThat()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("message", equalTo("Já existe um recurso do tipo Usuario com E-Mail com o valor 'usuario1@teste.com'."));
    }

    @Test
    @Order(16)
    void deveRetornarBadRequestAoTentarAlterarUsuarioComCepEmFormatoInvalido() {
        Address addressComCepInvalido = EnderecoFixture.get()
                .valido()
                .comCep("99999999")
                .build();
        UpdateUserRequest request = AtualizarUsuarioRequestFixture.get()
                .valido()
                .comEndereco(addressComCepInvalido)
                .build();

        String json = getObjectAsJson(request);

        given()
            .contentType(ContentType.JSON)
            .body(json)
        .when()
            .put(BASE_PATH + "/{idUsuario}", ID_USUARIO_CRIADO)
        .then()
            .assertThat()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("errors[0].defaultMessage", equalTo("O CEP deve ser informado no formato 99999-999."));
    }

    @Test
    @Order(17)
    void deveRetornarNotFoundAoTentarAlterarUsuarioComIdQueNaoExiste() {
        UpdateUserRequest request = AtualizarUsuarioRequestFixture.get().valido().build();

        String json = getObjectAsJson(request);

        given()
            .contentType(ContentType.JSON)
            .body(json)
        .when()
            .put(BASE_PATH + "/{idUsuario}", 999)
        .then()
            .assertThat()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body("message", equalTo("Nenhum(a) Usuario com Id com o valor '999' foi encontrado."));
    }

    @Test
    @Order(18)
    void deveAlterarUsuarioComSucesso() {
        UpdateUserRequest request = AtualizarUsuarioRequestFixture.get().valido().build();

        String json = getObjectAsJson(request);

        given()
            .contentType(ContentType.JSON)
            .body(json)
        .when()
            .put(BASE_PATH + "/{idUsuario}", ID_USUARIO_CRIADO)
        .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body("id", equalTo(ID_USUARIO_CRIADO))
            .body("nome", equalTo(request.getName()))
            .body("email", equalTo(request.getEmail()));
    }

}