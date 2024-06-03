package com.on.noted.api.domain.usuario;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.on.noted.api.domain.usuario.dto.DadosCadastro;
import com.on.noted.api.infra.security.TokenService;
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
        repository.save(new Usuario(dadosCriptografados));
    }

    public boolean validaSenha(String password, String passwordByEmail){
        return passwordEncoder.matches(passwordByEmail, password);
    }
    public String gerarToken(Usuario usuario){
        return tokenService.gerarToken(usuario);
    }
}
