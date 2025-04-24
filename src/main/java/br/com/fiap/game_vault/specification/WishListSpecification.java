package br.com.fiap.game_vault.specification;



import br.com.fiap.game_vault.model.WishList;
import br.com.fiap.game_vault.model.WishListFilter;
import org.springframework.data.jpa.domain.Specification;

public class WishListSpecification {

    public static Specification<WishList> withFilters(WishListFilter filter) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filter.game() != null && !filter.game().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("game")), "%" + filter.game().toLowerCase() + "%"));
            }

            if (filter.genero() != null && !filter.genero().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("genero")), "%" + filter.genero().toLowerCase() + "%"));
            }

            if (filter.status() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("status"), filter.status()));
            }

            return predicates;
        };
    }
}
