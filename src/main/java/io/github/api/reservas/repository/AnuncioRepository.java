package io.github.api.reservas.repository;

import io.github.api.reservas.domain.Anuncio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends CrudRepository<Anuncio, Long> {
    boolean existsByImovel_Id(Long idImovel);

    Page<Anuncio> findByAtivoTrue(Pageable pageable);

    Page<Anuncio> findByAnunciante_IdAndAtivoTrue(Pageable pageable, Long idAnunciante);

    boolean existsByAtivoTrueAndImovel_Id(Long idImovel);

}
