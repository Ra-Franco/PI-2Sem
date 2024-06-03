package com.on.noted.api.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastro(
        @NotBlank
        String email,
        @NotBlank
        String senha
) {
}
