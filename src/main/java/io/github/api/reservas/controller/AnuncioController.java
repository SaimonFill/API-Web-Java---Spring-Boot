package io.github.api.reservas.controller;

import io.github.api.reservas.domain.Anuncio;
import io.github.api.reservas.request.CadastrarAnuncioRequest;
import io.github.api.reservas.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    public ResponseEntity<Anuncio> cadastraAnuncio(@RequestBody @Valid CadastrarAnuncioRequest cadastrarAnuncioRequest) throws Exception {
        Anuncio anuncioCriado = anuncioService.cadastraAnuncio(cadastrarAnuncioRequest);
        return ResponseEntity.created(URI.create("/anuncios")).body(anuncioCriado);
    }

    @GetMapping
    public Page<Anuncio> listarAnuncios(@PageableDefault(sort = "valorDiaria", direction = Sort.Direction.ASC) Pageable pageable) {
        return anuncioService.listarAnuncios(pageable);
    }

    @GetMapping(path = "/anunciantes/{idAnunciante}")
    public Page<Anuncio> buscaAnuncioPorIdAnunciante(
            @PageableDefault(sort = "valorDiaria", direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable Long idAnunciante) {
        return anuncioService.buscaAnuncioPorIdAnunciante(pageable, idAnunciante);
    }

    @DeleteMapping(path = "/{idAnuncio}")
    public void excluirAnuncio(@PathVariable Long idAnuncio) throws Exception {
        anuncioService.excluirAnuncio(idAnuncio);
    }
}
