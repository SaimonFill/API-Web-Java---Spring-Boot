package io.github.api.reservas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SobreposicaoDatasException extends Exception {
    public SobreposicaoDatasException() {
        super("Este anuncio já esta reservado para o período informado.");
    }
}
