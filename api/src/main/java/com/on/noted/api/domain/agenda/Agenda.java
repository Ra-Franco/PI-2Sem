package com.on.noted.api.domain.agenda;

import com.on.noted.api.domain.agenda.dto.CadastroAgenda;
import com.on.noted.api.domain.evento.Evento;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Table(name = "agenda")
@Entity(name = "Agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @OneToMany(mappedBy = "agenda")
    private List<Evento> eventoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public Agenda(){

    }

    public Agenda(CadastroAgenda agenda){
        this.titulo = agenda.descricao();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agenda agenda)) return false;

        return Objects.equals(getId(), agenda.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
