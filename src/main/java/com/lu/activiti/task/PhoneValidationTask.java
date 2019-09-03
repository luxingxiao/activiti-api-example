package com.lu.activiti.task;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.lu.activiti.annotation.Input;
import com.lu.activiti.annotation.Output;

public class PhoneValidationTask implements IActivitiTask{

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
  }
}
