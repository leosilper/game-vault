package br.com.fiap.game_vault.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.game_vault.model.Category;

@RestController
public class CategoryController {
    
    private List<Category> repository = new ArrayList<>();

    //listar todas as categorias
    // GET :8080/categories -> json
    @GetMapping("/categories")
    public List<Category> index(){ //mochy
        return repository;
    }

    //cadastrando categoria
    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody Category category){
        System.out.println("Cadastrando categoria" + category.getJogo());
        System.out.println("Cadastrando categoria" + category.getPlataforma());
        repository.add(category);
        return ResponseEntity.status(201).body(category);
    }
    
    //retornar uma categoria
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> get(@PathVariable Long id){
        System.out.println("Buscando categoria " + id);
        var category = repository.stream()
        .filter(c -> c.getId().equals(id))
        .findFirst();

        if (category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(category.get());
    }
}
