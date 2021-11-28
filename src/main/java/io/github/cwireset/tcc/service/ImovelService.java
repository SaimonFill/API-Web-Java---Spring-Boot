package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.ConsultaIdInvalidoException;
import io.github.cwireset.tcc.exception.NaoExcluiImovelComAnuncioException;
import io.github.cwireset.tcc.repository.AnuncioRepository;
import io.github.cwireset.tcc.repository.ImovelRepository;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImovelService {

    private UsuarioService usuarioService;
    private ImovelRepository imovelRepository;
    private AnuncioRepository anuncioRepository;

    @Autowired
    public ImovelService(UsuarioService usuarioService, ImovelRepository imovelRepository, AnuncioRepository anuncioRepository) {
        this.usuarioService = usuarioService;
        this.imovelRepository = imovelRepository;
        this.anuncioRepository = anuncioRepository;
    }

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
        imovel.setAtivo(false);
        imovelRepository.save(imovel);
    }
}
