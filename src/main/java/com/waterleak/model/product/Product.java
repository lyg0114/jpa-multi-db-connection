package com.waterleak.model.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : iyeong-gyo
 * @package : com.waterleak.waterleak.model.product
 * @since : 2022/11/12
 */
@Entity
@Table(schema = "products")
public class Product {

  public Product() {}

  @Id
  private Long id;

  private String name;

  private Double price;

}
