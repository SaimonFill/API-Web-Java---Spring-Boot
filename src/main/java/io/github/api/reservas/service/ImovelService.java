package io.github.api.reservas.service;

import io.github.api.reservas.domain.Imovel;
import io.github.api.reservas.domain.Usuario;
import io.github.api.reservas.exception.ConsultaIdInvalidoException;
import io.github.api.reservas.exception.NaoExcluiImovelComAnuncioException;
import io.github.api.reservas.repository.AnuncioRepository;
import io.github.api.reservas.repository.ImovelRepository;
import io.github.api.reservas.request.CadastrarImovelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImovelService {

    private final UsuarioService usuarioService;
    private final ImovelRepository imovelRepository;
    private final AnuncioRepository anuncioRepository;

    public Imovel cadastraImovel(CadastrarImovelRequest cadastrarImovelRequest) throws Exception {

        final Usuario proprietario = usuarioService.buscaUsuarioPorId(cadastrarImovelRequest.getIdProprietario());

        final Imovel imovel = new Imovel(
                cadastrarImovelRequest.getIdentificacao(),
                cadastrarImovelRequest.getTipoImovel(),
                cadastrarImovelRequest.getEndereco(),
                proprietario,
                cadastrarImovelRequest.getCaracteristicas()
        );

        imovel.setAtivo(true);
        imovelRepository.save(imovel);
        return imovel;
    }

    public Page<Imovel> listarImoveis(Pageable pageable) {
        return imovelRepository.findByAtivoTrue(pageable);
    }

    public Page<Imovel> buscaImovelPorIdProprietario(Pageable pageable, Long idProprietario) throws Exception {
        return imovelRepository.findByAtivoTrueAndProprietario_Id(pageable, idProprietario);
    }

    public Imovel buscaImovelPorId(Long idImovel) throws Exception {
        boolean imovelAtivo = imovelRepository.existsByAtivoTrueAndId(idImovel);

        if(!imovelAtivo) {
            throw new ConsultaIdInvalidoException("Imovel", idImovel);
        }
        return imovelRepository.findByAtivoTrueAndId(idImovel);
    }

    public void excluirImovel(Long idImovel) throws Exception {
        boolean imovelPossuiAnuncio = anuncioRepository.existsByAtivoTrueAndImovel_Id(idImovel);

        if (imovelPossuiAnuncio) {
            throw new NaoExcluiImovelComAnuncioException();
        }

        Imovel imovel = imovelRepository.findById(idImovel)
                .orElseThrow(() -> new ConsultaIdInvalidoException("Imovel", idImovel));

        if (imovel.isAtivo() == false) {
            throw new ConsultaIdInvalidoException("Imovel", idImovel);
        }

        imovel.setAtivo(false);
        imovelRepository.save(imovel);
    }
}
