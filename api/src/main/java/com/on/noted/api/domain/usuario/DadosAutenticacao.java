package com.on.noted.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAutenticacao(
        @NotBlank
        String email,
        @NotBlank
        String senha) {
}
