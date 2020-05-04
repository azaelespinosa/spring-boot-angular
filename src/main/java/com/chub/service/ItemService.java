package com.chub.service;

import com.chub.dto.ItemDto;
import com.chub.exception.ResourceNotFoundException;
import com.chub.exception.UnprocessableException;
import com.chub.mode.ItemEntity;
import com.chub.repository.ItemRepository;
import com.chub.request.CreateItemRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemService extends BaseService<ItemRepository, ItemEntity>{

    public ItemDto createItem(CreateItemRequest itemDto){
        Optional<ItemEntity> optionalEntity  = repository.findByItemId(itemDto.getItemId());
        if (optionalEntity.isPresent()) {
            throw new UnprocessableException("The article already exists");
        }
       return create(itemDto , ItemDto.class);
    }

    public ItemDto updateItem(CreateItemRequest itemDto, Long id){
        return update(id, itemDto , ItemDto.class);
    }

    public List<ItemDto> findAllItems(){
        return findAll(ItemDto.class);
    }

    public ItemDto findItemByItemNo(Long itemNo) {
        ItemEntity entity  = getItemOr404(itemNo);
        return convertUtils.convert(entity, ItemDto.class);
    }

    public void deleteItem(Long itemNo){
        ItemEntity entity  = getItemOr404(itemNo);
        entity.setSoftDelete(true);
        entity.getItemStock().setSoftDelete(true);
        entity.getItemStock().setQty(0L);
        repository.save(entity);
    }

    private ItemEntity getItemOr404(Long itemNo) {
        Optional<ItemEntity> optionalEntity  = repository.findByItemId(itemNo);
        if (!optionalEntity.isPresent()) {
            throw new ResourceNotFoundException("ItemEntity","itemNo" , itemNo);
        }
        return optionalEntity.get();
    }

}
