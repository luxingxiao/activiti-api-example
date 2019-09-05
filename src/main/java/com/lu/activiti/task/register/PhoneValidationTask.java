package com.lu.activiti.task.register;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.lu.activiti.annotation.Input;
import com.lu.activiti.annotation.Output;
import com.lu.activiti.task.IActivitiTask;
import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
public class PhoneValidationTask implements IActivitiTask {

  @Input
  private int countryCode;
  @Input
  private long nationalNumber;
  @Output
  private boolean phoneValidated;

  @Override
  public void execute() {
    PhoneNumber phoneNumber = new PhoneNumber();
    phoneNumber.setCountryCode(countryCode);
    phoneNumber.setNationalNumber(nationalNumber);
    phoneValidated = PhoneNumberUtil.getInstance().isValidNumber(phoneNumber);
    log.info("手机号{} {}校验结果:{}", countryCode, nationalNumber, phoneValidated);
  }
}
