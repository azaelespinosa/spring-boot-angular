package com.chub.dto;

import lombok.Data;

@Data
public class ItemStockDto {

    private Long id;
    private Long itemId;
    private Long qty;
    private Long minStock;
    private Long maxStock;
}
