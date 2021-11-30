package io.github.api.reservas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImovelJaContemAnuncioException extends Exception {
    public ImovelJaContemAnuncioException(Long idImovel) {
        super(String.format("Já existe um recurso do tipo Anuncio com IdImovel com o valor '%d'.", idImovel));
    }
}