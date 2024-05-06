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
    private Long ev_id;
    private LocalDateTime ev_data;
    private String ev_descricao;

    @ManyToOne
    @JoinColumn(name = "agenda_id", nullable = false)
    private Agenda agenda;

    public Evento(Agenda agenda, DadosCriacaoEvento dados) {
        this.agenda = agenda;
        this.ev_data = dados.data();
        this.ev_descricao = dados.descricao();
    }

    public Long getEv_id() {
        return ev_id;
    }

    public void setEv_id(Long ev_id) {
        this.ev_id = ev_id;
    }

    public LocalDateTime getEv_data() {
        return ev_data;
    }

    public void setEv_data(LocalDateTime ev_data) {
        this.ev_data = ev_data;
    }

    public String getEv_descricao() {
        return ev_descricao;
    }

    public void setEv_descricao(String ev_descricao) {
        this.ev_descricao = ev_descricao;
    }

    public Evento(){}

    public Evento(Long ev_id, LocalDateTime ev_data, String ev_descricao, Agenda agenda) {
        this.ev_id = ev_id;
        this.ev_data = ev_data;
        this.ev_descricao = ev_descricao;
        this.agenda = agenda;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento evento)) return false;

        return Objects.equals(getEv_id(), evento.getEv_id()) && Objects.equals(getEv_data(), evento.getEv_data()) && Objects.equals(getEv_descricao(), evento.getEv_descricao());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getEv_id());
        result = 31 * result + Objects.hashCode(getEv_data());
        result = 31 * result + Objects.hashCode(getEv_descricao());
        return result;
    }
}
