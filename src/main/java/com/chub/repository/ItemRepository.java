package com.chub.repository;

import com.chub.mode.ItemEntity;

import java.util.Optional;

public interface ItemRepository extends BaseRepository<ItemEntity, Long>{
    Optional<ItemEntity> findByItemId(Long itemId);
}
