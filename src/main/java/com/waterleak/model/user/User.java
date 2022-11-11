package com.waterleak.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : iyeong-gyo
 * @package : com.waterleak.waterleak.model.user
 * @since : 2022/11/12
 */
@Entity
@Table(schema = "users")
public class User {

  public User() {}

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  private int age;

}
