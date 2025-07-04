package com.openbox.product.dto;

import com.openbox.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private int price;
    private String status;
    private String createdBy;

    public static ProductResponse fromEntity(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .status(product.getStatus())
                .createdBy(product.getCreatedBy())
                .build();
    }
}