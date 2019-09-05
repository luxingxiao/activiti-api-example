package com.lu.activiti.task.register;

import com.lu.activiti.annotation.Input;
import com.lu.activiti.mock.RedisMock;
import com.lu.activiti.mock.SmsMock;
import com.lu.activiti.task.IActivitiTask;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class SendVerifyCodeTask implements IActivitiTask {
  @Input
  private int countryCode;
  @Input
  private long nationalNumber;

  @Autowired
  private SmsMock smsService;
  @Autowired
  private RedisMock redis;
  @Override
  public void execute() {
    String phoneNumber = ""+countryCode+nationalNumber;
    String verifyCode = RandomStringUtils.randomNumeric(4);
    smsService.sendSmsMessage(phoneNumber, verifyCode);
    redis.addVerifyCode(phoneNumber, verifyCode);
  }
}
