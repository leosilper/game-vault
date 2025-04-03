package br.com.fiap.game_vault.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/games")
public class GamesController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GamesRepository repository;

    // listar todas os jogos
    // GET :8080/categories -> json
    @GetMapping("/games")
    public List<Games> index() { // mochy
        log.info("Buscando todos os Jogos");
        return repository.findAll();
    }

    // cadastrando jogo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Games create(@RequestBody @Valid Games game) {
        log.info("Cadastrando Jogos " + game.getGame());
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
