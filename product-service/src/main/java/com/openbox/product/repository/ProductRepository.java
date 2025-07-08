package com.openbox.product.repository;

import com.openbox.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCreatedBy(String createdBy); // 👈 내가 등록한 상품 조회용 메서드
}