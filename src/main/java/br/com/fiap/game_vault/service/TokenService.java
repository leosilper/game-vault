package br.com.fiap.game_vault.service;

import br.com.fiap.game_vault.model.Token;
import br.com.fiap.game_vault.model.User;
import br.com.fiap.game_vault.model.UserRole;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private static final Long DURATION = 60L;
    private static final Algorithm ALG = Algorithm.HMAC256("secret");

    public Token createToken(User user) {
        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().name())
                .withExpiresAt(LocalDateTime.now().plusMinutes(DURATION).toInstant(ZoneOffset.ofHours(-3)))
                .sign(ALG);

        return new Token(token, DURATION * 60, "Bearer", user.getRole().name());
    }

    public User getUserFromToken(String token) {
        var decoded = JWT.require(ALG).build().verify(token);

        return User.builder()
                .id(Long.parseLong(decoded.getSubject()))
                .email(decoded.getClaim("email").asString())
                .password("") // Necessário para não quebrar o contrato de UserDetails
                .role(UserRole.valueOf(decoded.getClaim("role").asString()))
                .build();
    }
}
