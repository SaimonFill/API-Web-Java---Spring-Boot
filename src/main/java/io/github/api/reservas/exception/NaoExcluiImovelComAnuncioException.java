package io.github.api.reservas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NaoExcluiImovelComAnuncioException extends Exception {
    public NaoExcluiImovelComAnuncioException() {
        super("Não é possível excluir um imóvel que possua um anúncio.");
    }
}
