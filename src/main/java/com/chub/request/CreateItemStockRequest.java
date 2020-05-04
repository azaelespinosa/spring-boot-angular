package com.chub.request;

import lombok.Data;

@Data
public class CreateItemStockRequest {

    private Long itemId;
    private Long qty;
    private Long minStock;
    private Long maxStock;
}
