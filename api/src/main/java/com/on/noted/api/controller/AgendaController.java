package com.on.noted.api.controller;

import com.on.noted.api.domain.agenda.Agenda;
import com.on.noted.api.domain.agenda.dto.CadastroAgenda;
import com.on.noted.api.domain.agenda.dto.DatasEventos;
import com.on.noted.api.domain.evento.DadosCriacaoEvento;
import com.on.noted.api.domain.evento.Evento;
import com.on.noted.api.domain.evento.EventoRepository;
import com.on.noted.api.service.AgendaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> listarAgendaId(@PathVariable Long id){
        var agenda = service.findAgendaId(id);
        return ResponseEntity.ok(agenda);
    }

    @PostMapping
    @Transactional
    public ResponseEntity adicionarAgenda(@RequestBody CadastroAgenda cadastroAgenda, UriComponentsBuilder uriBuilder){
        var agenda = new Agenda(cadastroAgenda);
        service.saveAgenda(agenda);
        var uri = uriBuilder.path("/agenda/{id}").buildAndExpand(agenda.getId()).toUri();
        return ResponseEntity.created(uri).body(agenda);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarAgenda(@PathVariable Long id){
        service.deleteAgendaById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/eventos")
    @Transactional
    public ResponseEntity<Map<LocalDate, List<Evento>>> getEventosByData(@PathVariable Long id, @RequestBody DatasEventos datas){
        var eventos = service.getEventosByData(id, datas);
        return ResponseEntity.ok(eventos);
    }


    @PostMapping("/{id}/eventos")
    @Transactional
    public ResponseEntity adicionarEvento(@PathVariable Long id,@RequestBody DadosCriacaoEvento dados){
        var dto = service.adicionaEvento(id, dados);
        return ResponseEntity.ok(dto);
    }

}