package com.on.noted.api.domain.agenda.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DatasEventos(
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dataIni,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dataFim) {
}
