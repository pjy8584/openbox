package com.openbox.product.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;              // 상품명
    private String description;       // 설명
    private String category;          // 카테고리 (노트북, 태블릿 등)
    private int price;                // 가격
    private String status;            // 상태 (예: WAITING_APPROVAL, APPROVED)

    private String createdBy;         // 등록자 (이메일 또는 닉네임)
}