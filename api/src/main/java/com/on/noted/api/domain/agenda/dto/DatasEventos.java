package com.on.noted.api.domain.agenda.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatasEventos(
        @JsonFormat(pattern = "dd-MM-yyyy")
        @NotNull
        LocalDate dataIni,
        @JsonFormat(pattern = "dd-MM-yyyy")
        @NotNull
        LocalDate dataFim) {
}
