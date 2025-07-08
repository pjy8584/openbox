package com.openbox.product.service;

import com.openbox.product.domain.Product;
import com.openbox.product.dto.AuctionCreateRequest;
import com.openbox.product.dto.ProductCreateRequest;
import com.openbox.product.dto.ProductResponse;
import com.openbox.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final RestTemplate restTemplate;
    
    // ✅ 상품 등록
    public void createProduct(ProductCreateRequest request, String createdBy) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .status("WAITING_APPROVAL")
                .createdBy(createdBy)
                .build();

        productRepository.save(product);

        // 👉 경매 등록 REST 호출
        AuctionCreateRequest auctionRequest = new AuctionCreateRequest(product.getId(), product.getPrice());
        restTemplate.postForEntity("http://localhost:8083/api/auctions", auctionRequest, Void.class);
    }

    // ✅ 상품 전체 조회
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        return ProductResponse.fromEntity(product); 
    }

    public List<ProductResponse> getMyProducts(String email) {
        List<Product> products = productRepository.findByCreatedBy(email);
        return products.stream()
                .map(ProductResponse::fromEntity)
                .toList();
    }

    public void deleteProduct(Long productId, String requesterEmail) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("해당 상품을 찾을 수 없습니다."));

        if (!product.getCreatedBy().equals(requesterEmail)) {
            throw new RuntimeException("상품을 삭제할 권한이 없습니다.");
        }

        productRepository.delete(product);
    }
}