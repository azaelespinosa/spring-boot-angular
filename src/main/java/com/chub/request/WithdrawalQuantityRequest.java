package com.chub.request;

import lombok.Data;

@Data
public class WithdrawalQuantityRequest {

    private Long itemId;
    private Long qty;
}
