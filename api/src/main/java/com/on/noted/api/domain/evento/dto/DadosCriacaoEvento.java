package com.on.noted.api.domain.evento.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCriacaoEvento(
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        @NotNull
        LocalDateTime data,
        @NotNull
        String descricao
) {
}
