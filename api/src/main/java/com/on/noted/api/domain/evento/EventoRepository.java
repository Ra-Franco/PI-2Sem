package com.on.noted.api.domain.evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    @Query(value = "select e.* from eventos e \n" +
            "where e.agenda_id = :agendaId \n" +
            "and e.ev_data between :dataIni and :dataFim"
            , nativeQuery = true)
    List<Evento> findEventoByDatas(Long agendaId, LocalDate dataIni, LocalDate dataFim);
}