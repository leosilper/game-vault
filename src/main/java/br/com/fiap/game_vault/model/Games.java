package br.com.fiap.game_vault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo obrigatório")
    private String game;

    @NotBlank(message = "campo obrigatório")
    private String genero;

    @NotBlank(message = "campo obrigatório")
    private String plataforma;

    @NotBlank(message = "campo obrigatório")
    private String status;

}
