package com.chub.request;

import lombok.Data;

@Data
public class AddQuantityRequest {

    private Long itemId;
    private Long qty;
}
