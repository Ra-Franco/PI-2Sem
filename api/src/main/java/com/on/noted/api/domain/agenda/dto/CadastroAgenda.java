package com.on.noted.api.domain.agenda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroAgenda(
        @NotBlank
        String descricao,
        @NotNull
        Long userId
) {
}
