package com.on.noted.api.domain.evento.dto;

import com.on.noted.api.domain.evento.Evento;

import java.time.LocalDateTime;

public record DadosEnvioEvento(
        Long id,
        String title,
        LocalDateTime start,
        LocalDateTime end,
        String desc,
        String color,
        String tipo
) {
    public DadosEnvioEvento(Evento evento){
        this(evento.getEvId(), evento.getTitulo(), evento.getEvDataIni(), evento.getEvDataFim(), evento.getEvDescricao(), evento.getEvCor(), evento.getTipo());
    }
}
