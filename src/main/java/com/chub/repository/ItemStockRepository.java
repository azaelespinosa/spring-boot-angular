package com.chub.repository;

import com.chub.mode.ItemStockEntity;

import java.util.Optional;

public interface ItemStockRepository extends BaseRepository<ItemStockEntity, Long>{

    Optional<ItemStockEntity> findByItemId(Long itemId);
}
