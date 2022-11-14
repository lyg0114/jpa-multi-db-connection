package com.waterleak.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.waterleak.WaterLeak;
import com.waterleak.config.Globals;
import com.waterleak.dao.reporting.AckNbiotRepository;
import com.waterleak.dto.AckNbiotDto;
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
public class ReportingServiceTest {

  @Autowired
  private ReportingService reportingService;
  @Autowired
  private AckNbiotRepository ackNbiotRepository;

  @Test
  @Transactional(Globals.REPORTING_TRANSACTION_MANAGER)
  public void AckNbiot_단건_조회() {
    AckNbiot instruct = AckNbiot.builder()
        .imei("123456789012345")
        .nbInstruction("instruct")
        .insertDate(Calendar.getInstance())
        .build();

    AckNbiot savedAckNbiot = ackNbiotRepository.save(instruct);
    final Optional<AckNbiot> result = ackNbiotRepository.findById(savedAckNbiot.getImei());
    assertTrue(result.isPresent());

    AckNbiotDto ackNbiotBy = reportingService.getAckNbiotBy(instruct.getImei());
    assertEquals(savedAckNbiot.getImei(), ackNbiotBy.getImei());
  }

}