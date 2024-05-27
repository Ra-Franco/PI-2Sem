package com.on.noted.api.domain.evento.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCriacaoEvento(
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        @NotNull
        LocalDateTime dataIni,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        @NotNull
        LocalDateTime dataFim,
        @NotNull
        String descricao
) {
}
