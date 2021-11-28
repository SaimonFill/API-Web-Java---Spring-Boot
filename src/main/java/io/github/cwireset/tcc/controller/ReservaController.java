package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.FormaPagamento;
import io.github.cwireset.tcc.domain.Periodo;
import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.request.CadastrarReservaRequest;
import io.github.cwireset.tcc.response.ReservaDTO;
import io.github.cwireset.tcc.response.InformacaoReservaResponse;
import io.github.cwireset.tcc.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedMap;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private ReservaDTO informacaoReservaResponse;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InformacaoReservaResponse criaReserva(@RequestBody @Valid CadastrarReservaRequest cadastrarReservaRequest) throws Exception {
        reservaService.criaReserva(cadastrarReservaRequest);
        return informacaoReservaResponse.reservaResponse(cadastrarReservaRequest);
    }

    @GetMapping(path = "/solicitantes/{idSolicitante}")
    public Page<Reserva> buscaReservaPorIdSolicitante
            (@PathVariable Long idSolicitante,
             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dataHoraFinal,
             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dataHoraInicial,
             @PageableDefault(sort = "periodo", direction = Sort.Direction.DESC) Pageable pageable) {
        return reservaService.buscaReservaPorIdSolicitante(idSolicitante, dataHoraFinal, dataHoraInicial, pageable);
    }

    @GetMapping(path = "/anuncios/anunciantes/{idAnunciante}")
    public Page<Reserva> buscaReservaPorIdAnunciante(
            @PathVariable Long idAnunciante,
            @PageableDefault(sort = "periodo", direction = Sort.Direction.DESC) Pageable pageable) {
        return reservaService.buscaReservaPorIdAnunciante(idAnunciante, pageable);
    }

    @PutMapping(path = "{idReserva}/pagamentos")
    public void pagarReserva(@PathVariable Long idReserva, @RequestBody FormaPagamento formaPagamento) throws Exception {
        reservaService.pagarReserva(idReserva, formaPagamento);
    }

    @PutMapping(path = "{idReserva}/pagamentos/cancelar")
    public void cancelarReserva(@PathVariable Long idReserva) throws Exception {
        reservaService.cancelarReserva(idReserva);
    }

    @PutMapping(path = "{idReserva}/pagamentos/estornar")
    public void estornarReserva(@PathVariable Long idReserva) throws Exception {
        reservaService.estornarReserva(idReserva);
    }
}
