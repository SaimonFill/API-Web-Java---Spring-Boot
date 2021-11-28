package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.request.CadastrarAnuncioRequest;
import io.github.cwireset.tcc.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
