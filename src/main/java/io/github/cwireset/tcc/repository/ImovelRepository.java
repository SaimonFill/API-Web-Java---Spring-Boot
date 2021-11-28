package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImovelRepository extends CrudRepository<Imovel, Long> {

    List<Imovel> findByProprietario_Id(Long idProprietario);

    Imovel findByAtivoTrueAndId(Long id);

    boolean existsByAtivoTrueAndId(Long idImovel);

    Page<Imovel> findByAtivoTrueAndProprietario_Id(Pageable pageable, Long idProprietario);

    Page<Imovel> findByAtivoTrue(Pageable pageable);
}
