package com.openbox.product.controller;

import com.openbox.product.dto.ProductCreateRequest;
import com.openbox.product.dto.ProductResponse;
import com.openbox.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List; // ✅ 이거 꼭 필요해!

@Tag(name = "상품 API", description = "상품 등록, 조회 등")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 등록")
    @PostMapping
    public ResponseEntity<String> createProduct(
            @RequestBody @Valid ProductCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String email = userDetails.getUsername(); // JWT에서 추출된 사용자 ID
        productService.createProduct(request, email);
        return ResponseEntity.ok("상품이 등록되었습니다 (승인 대기)");
    }

    @Operation(summary = "상품 전체 조회")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}