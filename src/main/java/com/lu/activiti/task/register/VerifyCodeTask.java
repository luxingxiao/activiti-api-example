package com.lu.activiti.task.register;

import com.lu.activiti.annotation.Input;
import com.lu.activiti.annotation.Output;
import com.lu.activiti.mock.RedisMock;
import com.lu.activiti.task.IActivitiTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
public class VerifyCodeTask implements IActivitiTask {
  @Input
  private int countryCode;
  @Input
  private long nationalNumber;
  @Input
  private String verifyCode;
  @Output
  private boolean verifyCodeValidated;
  @Output
  private String phoneNumber;
  @Autowired
  private RedisMock redis;

  @Override
  public void execute() {
    String phoneNumber = "" + countryCode + nationalNumber;
    Object cachedCode = redis.getVerifyCode(phoneNumber);
    if(cachedCode == null){
      verifyCodeValidated = false;
    }else{
      verifyCodeValidated = cachedCode.equals(verifyCode);
      this.phoneNumber = phoneNumber;
    }
    log.info("手机号{}，验证码{},校验结果为{}", phoneNumber, verifyCode, verifyCodeValidated);
  }
}
