package com.openbox.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuctionCreateRequest {
    private Long productId;
    private int startPrice;
}