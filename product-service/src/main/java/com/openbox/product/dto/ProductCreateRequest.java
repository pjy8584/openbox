package com.openbox.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductCreateRequest {

    @Schema(description = "상품명", example = "맥북 프로 14 M3")
    @NotBlank
    private String name;

    @Schema(description = "상품 설명", example = "새상품, 미개봉입니다.")
    private String description;

    @Schema(description = "카테고리", example = "노트북")
    @NotBlank
    private String category;

    @Schema(description = "상품 가격", example = "1800000")
    @NotNull
    private Integer price;

    // ✅ createdBy 제거됨 (JWT에서 추출할 예정)
}