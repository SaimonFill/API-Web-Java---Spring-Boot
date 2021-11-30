package io.github.api.reservas.repository;

import io.github.api.reservas.domain.FormaPagamento;
import io.github.api.reservas.domain.Reserva;
import io.github.api.reservas.domain.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {

    boolean existsByPagamento_StatusEqualsOrPagamento_StatusEquals(StatusPagamento cancelado, StatusPagamento estornado);

    Page<Reserva> findBySolicitante_Id(Long idSolicitante, Pageable pageable);

    Page<Reserva> findByPeriodo_DataHoraInicialBetweenAndPeriodo_DataHoraFinalBetweenAndSolicitante_Id(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal, LocalDateTime dataHoraInicial1, LocalDateTime dataHoraFinal1, Long idSolicitante, Pageable pageable);

    Page<Reserva> findByAnuncio_Anunciante_Id(Long idAnunciante, Pageable pageable);

    boolean existsByIdAndAnuncio_FormasAceitas(Long idReserva, FormaPagamento formaPagamento);

    boolean existsByPeriodo_DataHoraInicialLessThanAndAnuncio_Id(LocalDateTime dataHoraFinal, Long idAnuncio);

    Reserva findByPeriodo_DataHoraInicialEqualsAndPeriodo_DataHoraFinalEqualsAndAnuncio_Id(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal, Long idAnuncio);

    boolean existsByPeriodo_DataHoraFinalGreaterThanAndAnuncio_Id(LocalDateTime dataHoraInicial, Long idAnuncio);

    Page<Reserva> findByPeriodo_DataHoraInicialGreaterThanEqualAndPeriodo_DataHoraFinalLessThanEqualAndSolicitante_Id(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal, Long idSolicitante, Pageable pageable);

    Page<Reserva> findByPeriodo_DataHoraInicialEqualsAndSolicitante_Id(LocalDateTime dataHoraInicial, Long idSolicitante, Pageable pageable);

    Page<Reserva> findByPeriodo_DataHoraInicialEqualsAndPeriodo_DataHoraFinalEqualsAndSolicitante_Id(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal, Long idSolicitante, Pageable pageable);

    Page<Reserva> findByPeriodo_DataHoraInicialGreaterThanAndPeriodo_DataHoraFinalLessThanAndSolicitante_Id(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal, Long idSolicitante, Pageable pageable);
}
