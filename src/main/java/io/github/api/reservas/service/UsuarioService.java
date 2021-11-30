package io.github.api.reservas.service;

import io.github.api.reservas.exception.ConsultaCpfInvalidoException;
import io.github.api.reservas.exception.ConsultaIdInvalidoException;
import io.github.api.reservas.exception.CpfJaExisteException;
import io.github.api.reservas.exception.EmailJaExisteException;
import io.github.api.reservas.repository.UsuarioRepository;
import io.github.api.reservas.avatar.AvatarInterface;
import io.github.api.reservas.domain.Usuario;
import io.github.api.reservas.exception.*;
import io.github.api.reservas.request.AtualizarUsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    AvatarInterface avatarInterface;

    public Usuario cadastraUsuario(Usuario usuario) throws Exception {
        boolean emailDuplicado = usuarioRepository.existsByEmail(usuario.getEmail());
        boolean cpfDuplicado = usuarioRepository.existsByCpf(usuario.getCpf());

        if (emailDuplicado) {
            throw new EmailJaExisteException(usuario.getEmail());
        }
        if (cpfDuplicado) {
            throw new CpfJaExisteException(usuario.getCpf());
        }

        usuario = new Usuario(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getCpf(),
                usuario.getDataNascimento(),
                usuario.getEndereco(),
                avatarInterface.getAvatar().getLink()
        );

        usuarioRepository.save(usuario);
        return usuario;
    }

    public Page<Usuario> listarUsuarios(@PageableDefault Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Usuario buscaUsuarioPorId(Long id) throws Exception {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ConsultaIdInvalidoException("Usuario", id));
    }

    public Usuario consultarUsuarioPorCpf(String cpf) throws Exception {
        boolean usuarioCpf = usuarioRepository.existsByCpf(cpf);

        if (!usuarioCpf) {
            throw new ConsultaCpfInvalidoException(cpf);
        }
        return usuarioRepository.findByCpf(cpf);
    }

    public Usuario atualizarUsuario(Long id, AtualizarUsuarioRequest atualizarUsuarioRequest) throws Exception {
        Usuario usuario = buscaUsuarioPorId(id);
        boolean emailDuplicado = usuarioRepository.existsByEmail(atualizarUsuarioRequest.getEmail());

        if (!usuario.getEmail().equals(atualizarUsuarioRequest.getEmail())) {
            if (emailDuplicado) {
                throw new EmailJaExisteException(atualizarUsuarioRequest.getEmail());
            }
            usuario.setEmail(atualizarUsuarioRequest.getEmail());
        }
        if (usuario.getEndereco() == null) {
            usuario.setEndereco(atualizarUsuarioRequest.getEndereco());
        }
        if (atualizarUsuarioRequest.getEndereco() != null) {
            usuario.getEndereco().setBairro(atualizarUsuarioRequest.getEndereco().getBairro());
            usuario.getEndereco().setCep(atualizarUsuarioRequest.getEndereco().getCep());
            usuario.getEndereco().setCidade(atualizarUsuarioRequest.getEndereco().getCidade());
            usuario.getEndereco().setEstado(atualizarUsuarioRequest.getEndereco().getEstado());
            usuario.getEndereco().setLogradouro(atualizarUsuarioRequest.getEndereco().getLogradouro());
            usuario.getEndereco().setNumero(atualizarUsuarioRequest.getEndereco().getNumero());
            usuario.getEndereco().setComplemento(atualizarUsuarioRequest.getEndereco().getComplemento());
        }
        usuario.setDataNascimento(atualizarUsuarioRequest.getDataNascimento());
        usuario.setNome(atualizarUsuarioRequest.getNome());
        usuario.setSenha(atualizarUsuarioRequest.getSenha());

        usuarioRepository.save(usuario);
        return usuario;
    }
}
