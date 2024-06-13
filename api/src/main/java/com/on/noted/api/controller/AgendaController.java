package com.on.noted.api.controller;

import com.on.noted.api.domain.agenda.Agenda;
import com.on.noted.api.domain.agenda.dto.CadastroAgenda;
import com.on.noted.api.domain.agenda.dto.DatasEventos;
import com.on.noted.api.domain.evento.dto.DadosAlteracaoEvento;
import com.on.noted.api.domain.evento.dto.DadosCriacaoEvento;
import com.on.noted.api.domain.evento.Evento;
import com.on.noted.api.domain.evento.dto.DadosEnvioEvento;
import com.on.noted.api.service.AgendaService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public ResponseEntity<List<DadosEnvioEvento>> getEventosByData(@PathVariable Long id, @RequestParam String dataIni, @RequestParam String dataFim){
        var eventos = service.getEventosByData(id, dataIni, dataFim);
        List<DadosEnvioEvento> eventoDTOList = new ArrayList<>();
        for(Evento e : eventos){
            eventoDTOList.add(new DadosEnvioEvento(e));
        }
        return ResponseEntity.ok(eventoDTOList);
    }


    @PostMapping("/{id}/eventos")
    @Transactional
    public ResponseEntity adicionarEvento(@PathVariable Long id, @RequestBody @Valid DadosCriacaoEvento dados){
        var dto = service.adicionaEvento(id, dados);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/eventos/{idEvento}")
    @Transactional
    public ResponseEntity<DadosEnvioEvento> alterarEvento(@PathVariable Long id, @PathVariable Long idEvento,@RequestBody @Valid DadosAlteracaoEvento dados){
        Evento eventoDTO = service.alterarEvento(id, idEvento ,dados);
        System.out.println(eventoDTO + " " + id + " " + idEvento);
        return ResponseEntity.ok(new DadosEnvioEvento(eventoDTO));
    }

    @DeleteMapping("/{id}/eventos/{idEvento}")
    public ResponseEntity deletarEvento(@PathVariable Long id, @PathVariable Long idEvento){
        service.deleteEventoById(id, idEvento);
        return ResponseEntity.noContent().build();
    }

}
