package com.lu.activiti.task;

import com.lu.activiti.annotation.Input;
import com.lu.activiti.annotation.Output;
import com.lu.activiti.mock.RedisMock;
import org.springframework.beans.factory.annotation.Autowired;

public class VerifyCodeTask implements IActivitiTask{
  @Input
  private int countryCode;
  @Input
  private long nationalNumber;
  @Input
  private String verifyCode;
  @Output
  private boolean verifyCodeValidated;
  @Autowired
  private RedisMock redis;

  @Override
  public void execute() {

  }
}
