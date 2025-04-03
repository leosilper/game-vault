package br.com.fiap.game_vault.model;

import java.util.Random;

public class Game {
    private Long id;
    private String jogo;
    private String plataforma;
    private String genero;
    private String status;

    // construtores
    public Game(Long id, String jogo, String plataforma, String genero, String status) {
        this.id = Math.abs(new Random().nextLong());
        this.jogo = jogo;
        this.plataforma = plataforma;
        this.genero = genero;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getJogo() {
        return jogo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public String getStatus() {
        return status;
    }

}
