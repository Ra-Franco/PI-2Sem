package com.on.noted.api.domain.evento.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAlteracaoEvento(
        String title,
        String start,
        String end,
        String desc,
        String color,
        String tipo
) {
}
