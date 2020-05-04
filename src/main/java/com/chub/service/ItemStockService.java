package com.chub.service;

import com.chub.dto.ItemStockDto;
import com.chub.exception.ResourceNotFoundException;
import com.chub.exception.UnprocessableException;
import com.chub.request.AddQuantityRequest;
import com.chub.request.WithdrawalQuantityRequest;
import com.chub.mode.ItemStockEntity;
import com.chub.repository.ItemStockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemStockService  extends BaseService<ItemStockRepository, ItemStockEntity>{

    public List<ItemStockDto> findAllItemsStock(){
        return findAll(ItemStockDto.class);
    }

    public ItemStockDto withdrawalQuantityByItemNo(WithdrawalQuantityRequest dto) {
        ItemStockEntity itemStock  = getItemStockOr404(dto.getItemId());

        if (dto.getQty()>itemStock.getQty()) {
            throw new UnprocessableException("Incorrect amount to withdraw");
        }

        if(dto.getQty() - itemStock.getQty() < itemStock.getMinStock()){
            log.info("Minimum Stock validation.");
            //Send notification
        }

        itemStock.setQty(itemStock.getQty() - dto.getQty());
        return convertUtils.convert(repository.save(itemStock), ItemStockDto.class);
    }

    public ItemStockDto addQuantityByItemNo(AddQuantityRequest dto)  {
        ItemStockEntity itemStock  = getItemStockOr404(dto.getItemId());
        itemStock.setQty(dto.getQty() + itemStock.getQty());
        return convertUtils.convert(repository.save(itemStock), ItemStockDto.class);
    }

    private ItemStockEntity getItemStockOr404(Long itemNo) {
        Optional<ItemStockEntity> optionalEntity  = repository.findByItemId(itemNo);
        if (!optionalEntity.isPresent()) {
            throw new ResourceNotFoundException("ItemStockEntity","itemNo" , itemNo);
        }
        return optionalEntity.get();
    }


}
