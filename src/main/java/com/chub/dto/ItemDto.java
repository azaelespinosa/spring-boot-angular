package com.chub.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ItemDto {
    private Long id;
    private Long itemId;
    private Long amount;
    private String name;
    private Long invCode;

    private ItemStockDto itemStock;
}
