package com.openbox.auction.repository;

import com.openbox.auction.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByAuctionIdOrderByTimestampDesc(Long auctionId);
}