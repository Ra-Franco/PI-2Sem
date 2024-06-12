package com.on.noted.api.domain.usuario;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.on.noted.api.domain.agenda.Agenda;
import com.on.noted.api.domain.agenda.AgendaRepository;
import com.on.noted.api.domain.agenda.dto.CadastroAgenda;
import com.on.noted.api.domain.usuario.dto.DadosCadastro;
import com.on.noted.api.infra.security.TokenService;
import com.on.noted.api.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AgendaRepository agendaRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(13);

    @Override
    public UserDetails loadUserByUsername(String email) {
        try {
            return repository.findByEmail(email);
        } catch (UsernameNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }

    public void cadastrar(DadosCadastro dados){
        String encodedPassword = passwordEncoder.encode(dados.senha());
        DadosCadastro dadosCriptografados = new DadosCadastro(dados.email(), encodedPassword);
        Usuario usuario = repository.save(new Usuario(dadosCriptografados));
        var agenda = new Agenda(new CadastroAgenda(
                usuario.getUsername(),
                usuario.getId()
        ));
        agendaRepository.save(agenda);
    }

    public boolean validaSenha(String password, String passwordByEmail){
        return passwordEncoder.matches(passwordByEmail, password);
    }
    public String gerarToken(Usuario usuario){
        return tokenService.gerarToken(usuario);
    }
}
