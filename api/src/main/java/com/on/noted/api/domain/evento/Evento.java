package com.on.noted.api.domain.evento;

import com.on.noted.api.domain.agenda.Agenda;
import com.on.noted.api.domain.evento.dto.DadosCriacaoEvento;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Table(name = "eventos")
@Entity(name = "Evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ev_id")
    private Long EvId;
    @Column(name = "ev_titulo")
    private String titulo;
    @Column(name = "ev_data_ini")
    private LocalDateTime evDataIni;
    @Column(name = "ev_data_fim")
    private LocalDateTime evDataFim;
    @Column(name = "ev_descricao")
    private String evDescricao;
    @Column(name = "ev_cor")
    private String evCor;
    @Column(name = "ev_tipo")
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "agenda_id", nullable = false)
    private Agenda agenda;



    public Evento(Agenda agenda, DadosCriacaoEvento dados) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.titulo = dados.titulo();
        this.agenda = agenda;
        this.evDataIni = LocalDateTime.parse(dados.dataIni(), formatter);
        this.evDataFim = LocalDateTime.parse(dados.dataFim(), formatter);
        this.evDescricao = dados.descricao();
        this.evCor = dados.cor();
        this.tipo = dados.tipo();
    }

    public Long getEvId() {
        return EvId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getEvCor() {return evCor;}

    public void setEvDataIni(LocalDateTime evDataIni) {
        this.evDataIni = evDataIni;
    }

    public void setEvDataFim(LocalDateTime evDataFim) {
        this.evDataFim = evDataFim;
    }

    public void setEvCor(String evCor) {
        this.evCor = evCor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEvDescricao(String evDescricao) {
        this.evDescricao = evDescricao;
    }

    public Evento(){}

    public Evento(Long EvId, LocalDateTime evDataIni, String evDescricao, String evCor, Agenda agenda, String tipo, String titulo) {
        this.EvId = EvId;
        this.evDataIni = evDataIni;
        this.evDescricao = evDescricao;
        this.agenda = agenda;
        this.evCor = evCor;
        this.tipo = tipo;
        this.titulo = titulo;
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
