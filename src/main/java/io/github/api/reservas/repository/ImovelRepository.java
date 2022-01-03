package io.github.api.reservas.repository;

import io.github.api.reservas.domain.Imovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    List<Imovel> findByProprietario_Id(Long idProprietario);

    Imovel findByAtivoTrueAndId(Long id);

    boolean existsByAtivoTrueAndId(Long idImovel);

    Page<Imovel> findByAtivoTrueAndProprietario_Id(Pageable pageable, Long idProprietario);

    Page<Imovel> findByAtivoTrue(Pageable pageable);
}
