package br.com.fiap.game_vault.config;

import br.com.fiap.game_vault.model.*;
import br.com.fiap.game_vault.repository.GamesRepository;
import br.com.fiap.game_vault.repository.UserRepository;
import br.com.fiap.game_vault.repository.WishListRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final GamesRepository gamesRepository;
    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        // ✅ Cadastrar 3 usuários se ainda não existirem
        var users = List.of(
            User.builder()
                .email("user1@email.com")
                .password(passwordEncoder.encode("123456"))
                .role(UserRole.USER)
                .build(),

            User.builder()
                .email("user2@email.com")
                .password(passwordEncoder.encode("senha123"))
                .role(UserRole.USER)
                .build(),

            User.builder()
                .email("admin@email.com")
                .password(passwordEncoder.encode("admin123"))
                .role(UserRole.ADMIN)
                .build()
        );

        for (User user : users) {
            userRepository.findByEmail(user.getEmail())
                .orElseGet(() -> userRepository.save(user));
        }

        // ✅ Criar lista de jogos
        var games = List.of(
            Games.builder().game("The Legend of Zelda").genero("Aventura").build(),
            Games.builder().game("Super Mario Bros.").genero("Plataforma").build(),
            Games.builder().game("Minecraft").genero("Sandbox").build(),
            Games.builder().game("God of War").genero("Ação").build(),
            Games.builder().game("Red Dead Redemption").genero("Aventura").build(),
            Games.builder().game("Final Fantasy VII").genero("RPG").build(),
            Games.builder().game("The Last of Us").genero("Ação").build(),
            Games.builder().game("Resident Evil 4").genero("Terror").build(),
            Games.builder().game("Grand Theft Auto V").genero("Ação").build(),
            Games.builder().game("Hollow Knight").genero("Aventura").build(),
            Games.builder().game("Dark Souls III").genero("Ação").build(),
            Games.builder().game("FIFA 23").genero("Esporte").build(),
            Games.builder().game("Cyberpunk 2077").genero("RPG").build(),
            Games.builder().game("Call of Duty: Modern Warfare").genero("FPS").build(),
            Games.builder().game("Portal 2").genero("Ação").build(),
            Games.builder().game("Among Us").genero("Estratégia").build(),
            Games.builder().game("Celeste").genero("Plataforma").build(),
            Games.builder().game("Stardew Valley").genero("Simulação").build(),
            Games.builder().game("Overwatch").genero("FPS").build(),
            Games.builder().game("League of Legends").genero("Estratégia").build()
        );
        gamesRepository.saveAll(games);

        // ✅ Criar WishLists com jogos e status aleatórios
        var wishLists = new ArrayList<WishList>();
        var statuses = List.of(WishListType.PENDENTE, WishListType.COMPRADO);
        for (int i = 0; i < 30; i++) {
            var g = games.get(new Random().nextInt(games.size()));
            wishLists.add(WishList.builder()
                    .game(g.getGame())
                    .genero(g.getGenero())
                    .status(statuses.get(new Random().nextInt(statuses.size())))
                    .build());
        }
        wishListRepository.saveAll(wishLists);
    }
}
