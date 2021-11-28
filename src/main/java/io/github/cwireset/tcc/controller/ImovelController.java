package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import io.github.cwireset.tcc.service.ImovelService;
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
@RequestMapping("/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    @PostMapping
    public ResponseEntity<Imovel> cadastraImovel(@RequestBody @Valid CadastrarImovelRequest cadastrarImovelRequest) throws Exception {
        Imovel imovelCriado = imovelService.cadastraImovel(cadastrarImovelRequest);
        return ResponseEntity.created(URI.create("/imoveis")).body(imovelCriado);
    }

    @GetMapping
    public Page<Imovel> listarImoveis(@PageableDefault(sort = "identificacao", direction = Sort.Direction.ASC) Pageable pageable) {
        return imovelService.listarImoveis(pageable);
    }

    @GetMapping(path = "/proprietarios/{idProprietario}")
    public Page<Imovel> buscaImovelPorIdProprietario(
            @PageableDefault(sort = "identificacao", direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable Long idProprietario) throws Exception {
        return imovelService.buscaImovelPorIdProprietario(pageable, idProprietario);
    }

    @GetMapping(path = "/{idImovel}")
    public Imovel buscaImovelPorId(@PathVariable Long idImovel) throws Exception {
        return imovelService.buscaImovelPorId(idImovel);
    }

    @DeleteMapping(path = "/{idImovel}")
    public void excluirImovel(@PathVariable Long idImovel) throws Exception {
        imovelService.excluirImovel(idImovel);
    }
}
