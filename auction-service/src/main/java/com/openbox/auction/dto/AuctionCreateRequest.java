package com.openbox.auction.dto;

import lombok.Getter;

@Getter
public class AuctionCreateRequest {
    private Long productId;
    private int startPrice;
}