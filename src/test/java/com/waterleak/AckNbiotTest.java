package com.waterleak;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.model.reporting.AckNbiot;
import java.util.Calendar;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WaterLeak.class)
@EnableTransactionManagement
@ActiveProfiles("dev")
public class AckNbiotTest {

  @Autowired
  private AckNbiotRepository ackNbiotRepository;

  @Test
  @Transactional("reportingTransactionManager")
  public void whenAckNbiot_thenCreated() {
    AckNbiot instruct = AckNbiot.builder()
        .imei("123456789012345")
        .nbInstruction("instruct")
        .insertDate(Calendar.getInstance())
        .build();

    AckNbiot savedAckNbiot = ackNbiotRepository.save(instruct);
    final Optional<AckNbiot> result = ackNbiotRepository.findById(savedAckNbiot.getImei());
    assertTrue(result.isPresent());
  }
}
