package com.openbox.auction.dto;

import com.openbox.auction.domain.Auction;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AuctionResponse {
    private Long id;
    private Long productId;
    private int startPrice;
    private int currentPrice;
    private int highestBid;
    private String highestBidder;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    public static AuctionResponse fromEntity(Auction auction) {
        return AuctionResponse.builder()
                .id(auction.getId())
                .productId(auction.getProductId())
                .startPrice(auction.getStartPrice())
                .currentPrice(auction.getCurrentPrice())
                .highestBid(auction.getHighestBid())
                .highestBidder(auction.getHighestBidder())
                .startTime(auction.getStartTime())
                .endTime(auction.getEndTime())
                .status(auction.getStatus())
                .build();
    }
}