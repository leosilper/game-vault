package br.com.fiap.game_vault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
	info = @Info(
		title = "Game Vault API",
		version = "v1",
		description = "API do projeto Game vault",
		contact = @Contact(name = "Julio Oliveira", email = "dev.juliosamueloliveira@gmail.com")
	)
)
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
