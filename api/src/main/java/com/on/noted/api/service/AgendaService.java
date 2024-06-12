package com.on.noted.api.service;

import com.on.noted.api.domain.agenda.Agenda;
import com.on.noted.api.domain.agenda.AgendaRepository;
import com.on.noted.api.domain.agenda.dto.DatasEventos;
import com.on.noted.api.domain.evento.dto.DadosAlteracaoEvento;
import com.on.noted.api.domain.evento.dto.DadosCriacaoEvento;
import com.on.noted.api.domain.evento.Evento;
import com.on.noted.api.domain.evento.EventoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    @Autowired
    private EventoRepository eventoRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public HashMap<LocalDate, List<Evento>> getEventosByData(Long id, DatasEventos datasEventos) {
            var listEventos = eventoRepository.findEventoByDatas(id, datasEventos.dataIni(), datasEventos.dataFim());
            HashMap<LocalDate, List<Evento>> mapEventos = new HashMap<>();
            for (Evento e : listEventos) {
                adicionarEventos(e, mapEventos);
            }
            return mapEventos;
    }
    private void adicionarEventos(Evento evento, HashMap<LocalDate, List<Evento>> mapEventos){
        LocalDate dataEvento = evento.getEvDataIni().toLocalDate();
        if(!mapEventos.containsKey(dataEvento)){
            mapEventos.put(dataEvento, new ArrayList<>());
        }
        mapEventos.get(dataEvento).add(evento);
    }

    public Agenda findAgendaId(Long id) {
        return repository.findId(id);
    }

    public void saveAgenda(Agenda agenda) {
        repository.save(agenda);
    }

    public void deleteAgendaById(Long id) {
        repository.deleteById(id);
    }

    public Evento adicionaEvento(Long id, DadosCriacaoEvento dados) {
            Agenda agenda = repository.findId(id);
            Evento evento = new Evento(agenda, dados);
            return eventoRepository.save(evento);
    }

    public void deleteEventoById(Long idAgenda, Long idEvento){
        eventoRepository.deleteEventoById(idAgenda, idEvento);
    }

    public Evento alterarEvento(Long id, @Valid DadosAlteracaoEvento dados) {
        Optional<Evento> evento = eventoRepository.findEventoById(id ,dados.id());
        if(evento.isPresent()){
            Evento eventoExistente = evento.get();
            eventoExistente.setEvCor(dados.cor());
            eventoExistente.setEvDescricao(dados.descricao());
            eventoExistente.setEvDataFim(LocalDateTime.parse(dados.dataFim(), formatter));
            eventoExistente.setEvDataIni(LocalDateTime.parse(dados.dataIni(), formatter));
            eventoExistente.setTipo(dados.tipo());

            return eventoRepository.save(eventoExistente);
        }
        return null;
    }
}

