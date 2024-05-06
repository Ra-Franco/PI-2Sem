package com.on.noted.api.domain.agenda.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.on.noted.api.domain.evento.Evento;

import java.time.LocalDate;
import java.util.List;

public record DadosEventosPorData(
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate data,
        List<Evento> eventoList) {
}
