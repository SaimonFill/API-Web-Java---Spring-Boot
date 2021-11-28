package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MinimoDiariasPousadaException extends Exception {
    public MinimoDiariasPousadaException() {
        super("Não é possivel realizar uma reserva com menos de 5 diárias para imóveis do tipo Pousada");
    }
}
