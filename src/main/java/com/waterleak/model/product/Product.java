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
@Table(name = "product")
public class Product {

  public Product() {}

  @Id
  private Long id;

  private String name;

  private Double price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
