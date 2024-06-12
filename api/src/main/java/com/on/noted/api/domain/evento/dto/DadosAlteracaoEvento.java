package com.on.noted.api.domain.evento.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAlteracaoEvento(
        @NotNull
        Long id,
        @NotNull
        String titulo,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        @NotNull
        String dataIni,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        @NotNull
        String dataFim,
        @NotNull
        String descricao,
        String cor,
        String tipo
) {
}
