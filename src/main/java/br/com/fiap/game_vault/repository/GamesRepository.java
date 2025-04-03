package br.com.fiap.game_vault.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.game_vault.model.Games;

public interface GamesRepository extends JpaRepository<Games, Long> {

}
