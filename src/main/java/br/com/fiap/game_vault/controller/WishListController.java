package br.com.fiap.game_vault.controller;

import br.com.fiap.game_vault.model.WishList;
import br.com.fiap.game_vault.model.WishListFilter;
import br.com.fiap.game_vault.repository.WishListRepository;
import br.com.fiap.game_vault.specification.WishListSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wishlist")
@Slf4j
public class WishListController {

    @Autowired
    private WishListRepository repository;

    @GetMapping
    public Page<WishList> index(
            WishListFilter filter,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        var specification = WishListSpecification.withFilters(filter);
        return repository.findAll(specification, pageable);
    }

    @PostMapping
    public WishList create(@RequestBody WishList wishList) {
        return repository.save(wishList);
    }
}
