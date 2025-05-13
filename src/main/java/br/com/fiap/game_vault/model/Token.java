package br.com.fiap.game_vault.model;

public record Token(
    String token,
    Long expiration,
    String type,
    String role
) {}
