package com.openbox.product.service;

import com.openbox.product.domain.Product;
import com.openbox.product.dto.ProductCreateRequest;
import com.openbox.product.dto.ProductResponse;
import com.openbox.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

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
    }

    // ✅ 상품 전체 조회
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }
}