package com.on.noted.api.service;

import com.on.noted.api.domain.agenda.Agenda;
import com.on.noted.api.domain.agenda.AgendaRepository;
import com.on.noted.api.domain.agenda.dto.DatasEventos;
import com.on.noted.api.domain.evento.DadosCriacaoEvento;
import com.on.noted.api.domain.evento.Evento;
import com.on.noted.api.domain.evento.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    @Autowired
    private EventoRepository eventoRepository;

    public HashMap<LocalDate, List<Evento>> getEventosByData(Long id, DatasEventos datasEventos) {
            var listEventos = eventoRepository.findEventoByDatas(id, datasEventos.dataIni(), datasEventos.dataFim());
            HashMap<LocalDate, List<Evento>> mapEventos = new HashMap<>();
            for (Evento e : listEventos) {
                adicionarEventos(e, mapEventos);
            }
            return mapEventos;
    }
    private void adicionarEventos(Evento evento, HashMap<LocalDate, List<Evento>> mapEventos){
        LocalDate dataEvento = evento.getEv_data().toLocalDate();
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
}

