package com.openbox.auction.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.openbox.auction.domain.Auction;
import com.openbox.auction.domain.Bid;
import com.openbox.auction.dto.AuctionCreateRequest;
import com.openbox.auction.dto.AuctionResponse;
import com.openbox.auction.repository.AuctionRepository;
import com.openbox.auction.repository.BidRepository; // ✅ import 추가

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;
    
    public AuctionResponse createAuction(AuctionCreateRequest request) {
        Auction auction = Auction.builder()
                .productId(request.getProductId())
                .startPrice(request.getStartPrice())
                .currentPrice(request.getStartPrice())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusDays(3)) // 3일 후 종료
                .status("ACTIVE")
                .build();

        auctionRepository.save(auction);
        return AuctionResponse.fromEntity(auction);
    }

    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    public AuctionResponse getAuctionById(Long id) {
        Auction auction = auctionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 경매가 존재하지 않습니다."));
        return AuctionResponse.fromEntity(auction);
    }

    public void placeBid(Long auctionId, int bidAmount, String bidderEmail) {
        Auction auction = auctionRepository.findById(auctionId)
            .orElseThrow(() -> new RuntimeException("해당 경매를 찾을 수 없습니다."));

        if (LocalDateTime.now().isAfter(auction.getEndTime())) {
            throw new RuntimeException("경매가 종료되었습니다.");
        }

        if (bidAmount <= auction.getCurrentPrice()) {
            throw new RuntimeException("입찰 금액은 현재가보다 높아야 합니다.");
        }

        auction.setCurrentPrice(bidAmount);
        auction.setHighestBid(bidAmount);
        auction.setHighestBidder(bidderEmail);
        auctionRepository.save(auction);

        // ✅ 입찰 내역 저장 로직 추가
        Bid bid = Bid.builder()
                .auctionId(auctionId)
                .bidder(bidderEmail)
                .amount(bidAmount)
                .timestamp(LocalDateTime.now())
                .build();

        bidRepository.save(bid);
    }


}