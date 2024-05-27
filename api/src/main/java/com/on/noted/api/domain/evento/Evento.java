package com.on.noted.api.domain.evento;

import com.on.noted.api.domain.agenda.Agenda;
import com.on.noted.api.domain.evento.dto.DadosCriacaoEvento;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Table(name = "eventos")
@Entity(name = "Evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ev_id")
    private Long EvId;
    @Column(name = "ev_data_ini")
    private LocalDateTime evDataIni;
    @Column(name = "ev_data_fim")
    private LocalDateTime evDataFim;
    @Column(name = "ev_descricao")
    private String evDescricao;

    @ManyToOne
    @JoinColumn(name = "agenda_id", nullable = false)
    private Agenda agenda;

    public Evento(Agenda agenda, DadosCriacaoEvento dados) {
        this.agenda = agenda;
        this.evDataIni = dados.dataIni();
        this.evDataFim = dados.dataFim();
        this.evDescricao = dados.descricao();
    }

    public Long getEvId() {
        return EvId;
    }

    public LocalDateTime getEvDataIni() {
        return evDataIni;
    }

    public String getEvDescricao() {
        return evDescricao;
    }

    public LocalDateTime getEvDataFim() {
        return evDataFim;
    }

    public void setEvDescricao(String evDescricao) {
        this.evDescricao = evDescricao;
    }

    public Evento(){}

    public Evento(Long EvId, LocalDateTime evDataIni, String evDescricao, Agenda agenda) {
        this.EvId = EvId;
        this.evDataIni = evDataIni;
        this.evDescricao = evDescricao;
        this.agenda = agenda;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento evento)) return false;

        return Objects.equals(getEvId(), evento.getEvId()) && Objects.equals(getEvDataIni(), evento.getEvDataIni()) && Objects.equals(getEvDescricao(), evento.getEvDescricao());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getEvId());
        result = 31 * result + Objects.hashCode(getEvDataIni());
        result = 31 * result + Objects.hashCode(getEvDescricao());
        return result;
    }
}
