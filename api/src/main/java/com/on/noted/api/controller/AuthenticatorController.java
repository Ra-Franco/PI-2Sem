package com.on.noted.api.controller;

import com.on.noted.api.domain.usuario.UsuarioService;
import com.on.noted.api.domain.usuario.dto.DadosAutenticacao;
import com.on.noted.api.domain.usuario.Usuario;
import com.on.noted.api.domain.usuario.dto.DadosCadastro;
import com.on.noted.api.infra.security.TokenService;
import com.on.noted.api.infra.security.dto.DadosTokenJWT;
import com.sun.tools.jconsole.JConsoleContext;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticatorController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(13);

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var user = usuarioService.loadUserByUsername(dados.email());
        boolean passwordValidated = usuarioService.validaSenha(user.getPassword(), dados.senha());

        if (!passwordValidated){
            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
        }
        Usuario usuario = (Usuario) user;
        String tokenJWT = usuarioService.gerarToken(usuario);
        return ResponseEntity.ok(new DadosTokenJWT(usuario.getId(), tokenJWT));
    }
    @PostMapping("/cadastrar")
    public ResponseEntity efetuarCadastro(@RequestBody @Valid DadosCadastro dados){
        usuarioService.cadastrar(dados);
        return ResponseEntity.ok().build();
    }

}
