package br.com.fiap.game_vault.config;

import br.com.fiap.game_vault.model.Games;
import br.com.fiap.game_vault.model.WishList;
import br.com.fiap.game_vault.model.WishListType;
import br.com.fiap.game_vault.repository.GamesRepository;
import br.com.fiap.game_vault.repository.WishListRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder {

    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @PostConstruct
    public void init() {
        // Criação de jogos
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

        // Criação de WishList
        var wishLists = new ArrayList<WishList>();
        var statuses = List.of(WishListType.PENDENTE, WishListType.COMPRADO);
        for (int i = 0; i < 30; i++) {
            wishLists.add(WishList.builder()
                    .game(games.get(new Random().nextInt(games.size())).getGame()) // Pega o nome de um jogo da lista
                    .genero(games.get(new Random().nextInt(games.size())).getGenero()) // Pega o gênero de um jogo
                    .status(statuses.get(new Random().nextInt(statuses.size()))) // Status aleatório
                    .build());
        }

        wishListRepository.saveAll(wishLists); // Salva as wishlists no banco
    }
}
