package com.openbox.auction.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private int startPrice;

    private int currentPrice;

    private String highestBidder;

    private int highestBid;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String status;

    // ✅ 현재 가격 변경 메서드 추가
    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    // ✅ 최고 입찰자 변경 메서드도 함께 추가
    public void setHighestBidder(String highestBidder) {
        this.highestBidder = highestBidder;
    }

    public void setHighestBid(int highestBid) {
        this.highestBid = highestBid;
    }
}