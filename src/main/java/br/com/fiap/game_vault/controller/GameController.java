package br.com.fiap.game_vault.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.game_vault.model.Game;

@RestController
public class GameController {

    private List<Game> repository = new ArrayList<>();

    // listar todas as categorias
    // GET :8080/categories -> json
    @GetMapping("/categories")
    public List<Game> index() { // mochy
        return repository;
    }

    // cadastrando categoria
    @PostMapping("/categories")
    public ResponseEntity<Game> create(@RequestBody Game Game) {
        System.out.println("Cadastrando Jogo" + Game.getJogo());
        System.out.println("Cadastrando Jogo" + Game.getPlataforma());
        repository.add(Game);
        return ResponseEntity.status(201).body(Game);
    }

    // retornar uma categoria
    @GetMapping("/categories/{id}")
    public ResponseEntity<Game> get(@PathVariable Long id) {
        System.out.println("Buscando Jogo " + id);
        var game = repository.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (game.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(game.get());
    }
}
