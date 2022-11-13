package com.waterleak.schedule;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WaterLeakSchedule {
  @Scheduled(cron = "* * * * * *")
  public void test(){
    System.out.println("##########");
  }
}