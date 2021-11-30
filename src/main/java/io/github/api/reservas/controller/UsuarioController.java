package io.github.api.reservas.controller;

import io.github.api.reservas.domain.Usuario;
import io.github.api.reservas.request.AtualizarUsuarioRequest;
import io.github.api.reservas.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastraUsuario(@RequestBody @Valid Usuario usuario) throws Exception {
        Usuario usuarioSalvo = usuarioService.cadastraUsuario(usuario);
        return ResponseEntity.created(URI.create("/usuarios")).body(usuarioSalvo);
    }

    @GetMapping
    public Page<Usuario> listarUsuarios(@PageableDefault(sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return usuarioService.listarUsuarios(pageable);
    }

    @GetMapping(path = "/{idUsuario}")
    public Usuario consultarUsuarioPorId(@PathVariable Long idUsuario) throws Exception {
        return usuarioService.buscaUsuarioPorId(idUsuario);
    }

    @GetMapping(path = "/cpf/{cpf}")
    public Usuario consultarUsuarioPorCpf(@PathVariable String cpf) throws Exception {
        return usuarioService.consultarUsuarioPorCpf(cpf);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable @Valid Long id, @RequestBody @Valid AtualizarUsuarioRequest atualizarUsuarioRequest) throws Exception {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, atualizarUsuarioRequest);
        return ResponseEntity.ok().body(usuarioAtualizado);
    }
}
