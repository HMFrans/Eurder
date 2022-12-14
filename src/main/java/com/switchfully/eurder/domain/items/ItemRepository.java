package com.switchfully.eurder.domain.items;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    Item findByName(String name);
    Boolean existsByName(String name);
}
