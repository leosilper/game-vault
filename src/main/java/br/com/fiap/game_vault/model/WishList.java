package br.com.fiap.game_vault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //faz com que seja salvo no banco de dados como uma tabela
@Data //Gera todos os gets,sets e construtores sem precisar poluir a classe
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class WishList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo obrigatório")
    private String game;

    @NotBlank(message = "campo obrigatório")
    private String genero;

    @Enumerated(EnumType.STRING)
    private WishListType status;
}
