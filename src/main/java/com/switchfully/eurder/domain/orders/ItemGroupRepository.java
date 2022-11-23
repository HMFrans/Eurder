package com.switchfully.eurder.domain.orders;

import com.switchfully.eurder.domain.items.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, Integer> {
    List<ItemGroup> findAllByOrder(Order order);
}
