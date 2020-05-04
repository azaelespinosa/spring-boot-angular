package com.chub.request;

import com.chub.dto.ItemStockDto;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreateItemRequest {

    private Long itemId;
    private Long amount;
    private String name;
    private Long invCode;

    private CreateItemStockRequest itemStock;
}
