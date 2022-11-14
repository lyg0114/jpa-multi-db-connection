package com.waterleak.dao.wapi;

import com.waterleak.model.wapi.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
