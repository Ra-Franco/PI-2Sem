package com.on.noted.api.domain.evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    @Query(value = "select e.ev_id,e.ev_data_ini, e.ev_data_fim, e.ev_descricao, e.ev_cor,e.agenda_id from eventos e\n" +
            "where e.agenda_id = :agendaId\n" +
            "and e.ev_data_ini >= :dataIni\n" +
            "and e.ev_data_fim <= :dataFim"
            , nativeQuery = true)
    List<Evento> findEventoByDatas(Long agendaId, LocalDate dataIni, LocalDate dataFim);

    @Query(value = "select e.* from eventos e\n" +
            "where e.agenda_id = :agendaId \n" +
            "and e.ev_id = :id"
            , nativeQuery = true)
    Optional<Evento> findEventoById(Long agendaId, Long id);

    @Query(value = "DELETE FROM eventos \n" +
            "WHERE evId = :eventoId \n" +
            "AND agenda_id = :agendaId", nativeQuery = true)
    void deleteEventoById(Long agendaId, Long eventoId);
}
