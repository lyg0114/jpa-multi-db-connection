package com.waterleak;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.fail;

import com.waterleak.dao.product.ProductRepository;
import com.waterleak.dao.user.UserRepository;
import com.waterleak.model.product.Product;
import com.waterleak.model.user.User;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : iyeong-gyo
 * @package : com.waterleak
 * @since : 2022/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=WaterLeak.class)
@ActiveProfiles("dev")
@EnableTransactionManagement
public class JpaMultipleDBIntegrationTest {
  @Autowired private UserRepository userRepository;
  @Autowired private ProductRepository productRepository;

  private final String USRT_TRANSACTION_MANAGER = "userTransactionManager";
  private final String PRODUCT_TRANSACTION_MANAGER = "productTransactionManager";

  @Test
  @Transactional(USRT_TRANSACTION_MANAGER)
  public void whenCreatingUser_thenCreated() {
    User user = new User();
    user.setName("John");
    user.setEmail("john@test.com");
    user.setAge(20);
    user = userRepository.save(user);
    final Optional<User> result = userRepository.findById(user.getId());
    assertTrue(result.isPresent());
  }

  @Test
  @Transactional(USRT_TRANSACTION_MANAGER)
  public void whenCreatingUsersWithSameEmail_thenRollback() {
    User user1 = new User();
    user1.setName("John");
    user1.setEmail("john@test.com");
    user1.setAge(20);
    user1 = userRepository.save(user1);
    assertTrue(userRepository.findById(user1.getId()).isPresent());

    User user2 = new User();
    user2.setName("Tom");
    user2.setEmail("john@test.com");
    user2.setAge(10);
    try {
      user2 = userRepository.save(user2);
      userRepository.flush();
      fail("DataIntegrityViolationException should be thrown!");
    } catch (final DataIntegrityViolationException e) {
      // Expected
    } catch (final Exception e) {
      fail("DataIntegrityViolationException should be thrown, instead got: " + e);
    }
  }

  @Test
  @Transactional(PRODUCT_TRANSACTION_MANAGER)
  public void whenCreatingProduct_thenCreated() {
    Product product = new Product();
    product.setName("Book");
    product.setId(2L);
    product.setPrice(20.0);
    product = productRepository.save(product);
    assertTrue(productRepository.findById(product.getId()).isPresent());
  }
}
