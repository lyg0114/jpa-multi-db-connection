package com.waterleak.waterleak.dao.product;

import com.waterleak.waterleak.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : iyeong-gyo
 * @package : com.waterleak.waterleak.dao.product
 * @since : 2022/11/12
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
