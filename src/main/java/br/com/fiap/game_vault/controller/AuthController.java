package br.com.fiap.game_vault.controller;

import br.com.fiap.game_vault.model.Credentials;
import br.com.fiap.game_vault.model.Token;
import br.com.fiap.game_vault.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Token login(@RequestBody @Valid Credentials credentials) {
        return authService.login(credentials);
    }
}
