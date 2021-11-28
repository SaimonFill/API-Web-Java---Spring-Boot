package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MinimoPessoasHotelException extends Exception {
    public MinimoPessoasHotelException() {
        super("Não é possivel realizar uma reserva com menos de 2 pessoas para imóveis do tipo Hotel");
    }
}
