package com.openbox.auction.controller;
import java.util.List;

import com.openbox.auction.domain.Auction;
import com.openbox.auction.domain.Bid;
import com.openbox.auction.dto.AuctionCreateRequest;
import com.openbox.auction.dto.AuctionResponse;
import com.openbox.auction.dto.BidRequest;
import com.openbox.auction.service.AuctionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.openbox.auction.repository.BidRepository;


@Tag(name = "경매 API", description = "경매 등록/조회 등")
@RestController
@RequestMapping("/api/auctions")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;
     private final BidRepository bidRepository;
     
    @Operation(summary = "경매 생성")
    @PostMapping
    public ResponseEntity<AuctionResponse> createAuction(@RequestBody AuctionCreateRequest request) {
        AuctionResponse response = auctionService.createAuction(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "전체 경매 조회")
    @GetMapping
    public ResponseEntity<List<Auction>> getAllAuctions() {
        List<Auction> auctions = auctionService.getAllAuctions();
        return ResponseEntity.ok(auctions);
    }

    @Operation(summary = "경매 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<AuctionResponse> getAuctionById(@PathVariable Long id) {
        AuctionResponse response = auctionService.getAuctionById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "경매 입찰")
    @PostMapping("/{id}/bid")
    public ResponseEntity<String> placeBid(
            @PathVariable Long id,
            @RequestBody BidRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String email = userDetails.getUsername();
        auctionService.placeBid(id, request.getBidAmount(), email);
        return ResponseEntity.ok("입찰이 완료되었습니다.");
    }

    @Operation(summary = "경매 입찰 내역 조회")
    @GetMapping("/{id}/bids")
    public ResponseEntity<List<Bid>> getBidHistory(@PathVariable Long id) {
        List<Bid> bids = bidRepository.findByAuctionIdOrderByTimestampDesc(id);
        return ResponseEntity.ok(bids);
    }
}