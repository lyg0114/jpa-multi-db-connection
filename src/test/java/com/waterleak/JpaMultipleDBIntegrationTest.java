package com.waterleak;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.waterleak.dao.product.ProductRepository;
import com.waterleak.dao.user.UserRepository;
import com.waterleak.model.user.User;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
@EnableTransactionManagement
public class JpaMultipleDBIntegrationTest {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ProductRepository productRepository;

  @Test
  @Transactional("userTransactionManager")
  public void whenCreatingUser_thenCreated() {
    User user = new User();
    user.setName("John");
    user.setEmail("john@test.com");
    user.setAge(20);
    user = userRepository.save(user);
    final Optional<User> result = userRepository.findById(user.getId());
    assertTrue(result.isPresent());
  }
}
