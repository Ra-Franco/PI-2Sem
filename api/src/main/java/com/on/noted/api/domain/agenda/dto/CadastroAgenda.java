package com.on.noted.api.domain.agenda.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroAgenda(
        @NotBlank
        String descricao) {
}
