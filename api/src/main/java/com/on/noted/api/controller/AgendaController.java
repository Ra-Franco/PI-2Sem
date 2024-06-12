package com.on.noted.api.controller;

import com.on.noted.api.domain.agenda.Agenda;
import com.on.noted.api.domain.agenda.dto.CadastroAgenda;
import com.on.noted.api.domain.agenda.dto.DatasEventos;
import com.on.noted.api.domain.evento.dto.DadosAlteracaoEvento;
import com.on.noted.api.domain.evento.dto.DadosCriacaoEvento;
import com.on.noted.api.domain.evento.Evento;
import com.on.noted.api.service.AgendaService;
import jakarta.validation.Valid;
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
@CrossOrigin(origins = "*")
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
    public ResponseEntity adicionarAgenda(@RequestBody @Valid CadastroAgenda cadastroAgenda, UriComponentsBuilder uriBuilder){
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
    public ResponseEntity<Map<LocalDate, List<Evento>>> getEventosByData(@PathVariable Long id, @PathVariable String dataIni, String dataFim){
        var eventos = service.getEventosByData(id, dataIni, dataFim);
        return ResponseEntity.ok(eventos);
    }


    @PostMapping("/{id}/eventos")
    @Transactional
    public ResponseEntity adicionarEvento(@PathVariable Long id,@RequestBody @Valid DadosCriacaoEvento dados){
        var dto = service.adicionaEvento(id, dados);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/eventos")
    @Transactional
    public ResponseEntity alterarEvento(@PathVariable Long id, @RequestBody @Valid DadosAlteracaoEvento dados){
        var dto = service.alterarEvento(id, dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}/eventos/{idEvento}")
    public ResponseEntity deletarEvento(@PathVariable Long id, @PathVariable Long idEvento){
        service.deleteEventoById(id, idEvento);
        return ResponseEntity.noContent().build();
    }

}
