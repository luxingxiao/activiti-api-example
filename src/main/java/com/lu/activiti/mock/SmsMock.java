package com.lu.activiti.mock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsMock {
  public void sendSmsMessage(String phoneNumber, String verifyCode){
    log.info("{},您此次注册的验证码为{}", phoneNumber, verifyCode);
  }
}
