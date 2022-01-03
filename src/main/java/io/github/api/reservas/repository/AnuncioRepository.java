package io.github.api.reservas.repository;

import io.github.api.reservas.domain.Anuncio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    Page<Anuncio> findByAtivoTrue(Pageable pageable);

    Page<Anuncio> findByAnunciante_IdAndAtivoTrue(Pageable pageable, Long idAnunciante);

    boolean existsByAtivoTrueAndImovel_Id(Long idImovel);

}
