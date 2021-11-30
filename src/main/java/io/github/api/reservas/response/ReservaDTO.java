package io.github.api.reservas.response;

import io.github.api.reservas.domain.Anuncio;
import io.github.api.reservas.domain.Reserva;
import io.github.api.reservas.domain.Usuario;
import io.github.api.reservas.request.CadastrarReservaRequest;
import io.github.api.reservas.service.AnuncioService;
import io.github.api.reservas.service.ReservaService;
import io.github.api.reservas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaDTO {

    private ReservaService reservaService;
    private DadosSolicitanteResponse solicitante;
    private UsuarioService usuarioService;
    private DadosAnuncioResponse anuncio;
    private AnuncioService anuncioService;

    @Autowired
    public ReservaDTO(ReservaService reservaService, UsuarioService usuarioService, AnuncioService anuncioService) {
        this.reservaService = reservaService;
        this.usuarioService = usuarioService;
        this.anuncioService = anuncioService;
    }

    public InformacaoReservaResponse reservaResponse(CadastrarReservaRequest cadastrarReservaRequest) throws Exception {
        final Usuario nomeSolicitante = usuarioService.buscaUsuarioPorId(cadastrarReservaRequest.getIdSolicitante());
        final Anuncio dadosAnuncio = anuncioService.buscaAnuncioPorId(cadastrarReservaRequest.getIdAnuncio());
        final Reserva dadosReserva = reservaService.buscaReservaResponse(cadastrarReservaRequest);

        return new InformacaoReservaResponse(
                dadosReserva.getId(),
                solicitante = new DadosSolicitanteResponse(
                        cadastrarReservaRequest.getIdSolicitante(),
                        nomeSolicitante.getNome()
                ),
                dadosReserva.getQuantidadePessoas(),
                anuncio = new DadosAnuncioResponse(
                        dadosAnuncio.getId(),
                        dadosAnuncio.getImovel(),
                        dadosAnuncio.getAnunciante(),
                        dadosAnuncio.getFormasAceitas(),
                        dadosAnuncio.getDescricao()
                ),
                dadosReserva.getPeriodo(),
                dadosReserva.getPagamento()
        );
    }
}
