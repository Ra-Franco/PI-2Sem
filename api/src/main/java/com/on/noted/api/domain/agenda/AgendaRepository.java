package com.on.noted.api.domain.agenda;

import com.on.noted.api.domain.evento.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface AgendaRepository extends JpaRepository <Agenda, Long> {
    @Query("Select a from Agenda a \n" +
            "where a.userId = :id")
    Agenda findId(Long id);

}
