package br.com.fiap.game_vault.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.game_vault.model.Games;
import br.com.fiap.game_vault.repository.GamesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/games")
@Slf4j
public class GamesController {

    @Autowired
    private GamesRepository repository;

    // listar todas os jogos
    // GET :8080/categories -> json
    @GetMapping
    @Cacheable("/games")
    @Operation(description = "Listar todos os jogos", tags = "games", summary = "Lista de jogos")
    public List<Games> index() { // mochy
        log.info("Buscando todos os Jogos");
        return repository.findAll();
    }

    // cadastrando jogo
    @PostMapping
    @CacheEvict(value = "games", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
     @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public Games create(@RequestBody @Valid Games game) {
        log.info("Cadastrando Jogo " + game.getGame());
        return repository.save(game);
    }

    // retornar uma categoria
    @GetMapping("{id}")
    public Games get(@PathVariable Long id) {
        log.info("Buscando Jogos " + id);
        return getGames(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando Jogo " + id);
        repository.delete(getGames(id));
    }

    @PutMapping("{id}")
    public Games update(@PathVariable Long id, @RequestBody @Valid Games game) {
        log.info("Atualizando Jogo " + id + " " + game);

        getGames(id);
        game.setId(id);
        return repository.save(game);
    }

    private Games getGames(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Jogo não encontrado"));
    }
}
