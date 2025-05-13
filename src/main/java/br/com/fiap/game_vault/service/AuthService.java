package br.com.fiap.game_vault.service;

import br.com.fiap.game_vault.model.Credentials;
import br.com.fiap.game_vault.model.Token;
import br.com.fiap.game_vault.model.User;
import br.com.fiap.game_vault.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;

    public Token login(Credentials credentials) {
        User user = userRepository.findByEmail(credentials.email())
            .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        if (!encoder.matches(credentials.password(), user.getPassword()))
            throw new RuntimeException("Email ou senha inválidos");

        return tokenService.createToken(user);
    }
}
