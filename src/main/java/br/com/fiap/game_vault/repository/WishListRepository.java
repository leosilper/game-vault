package br.com.fiap.game_vault.repository;

import br.com.fiap.game_vault.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WishListRepository extends JpaRepository<WishList, Long>, JpaSpecificationExecutor<WishList> {
}
