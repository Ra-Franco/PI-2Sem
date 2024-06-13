package com.on.noted.api.domain.evento;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    @Query(value = "select e.ev_id,e.ev_data_ini, e.ev_data_fim, e.ev_descricao, e.ev_cor, e.ev_tipo,e.ev_titulo, e.agenda_id from eventos e\n" +
            "right join agenda a \n" +
            "on a.id  = e.agenda_id \n" +
            "where a.user_id = :userId\n" +
            "and e.ev_data_ini >= :dataIni\n" +
            "and e.ev_data_fim <= :dataFim"
            , nativeQuery = true)
    List<Evento> findEventoByDatas(Long userId, LocalDate dataIni, LocalDate dataFim);

    @Query(value = "select e.* from eventos e\n " +
            "left join agenda a \n" +
            "on a.id = e.agenda_id \n" +
            "where a.user_id = :agendaId \n" +
            "and e.ev_id = :id"
            , nativeQuery = true)
    Optional<Evento> findEventoById(Long agendaId, Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM eventos \n" +
            "WHERE ev_id = :eventoId \n" +
            "AND agenda_id = :agendaId", nativeQuery = true)
    void deleteEventoById(Long agendaId, Long eventoId);
}
