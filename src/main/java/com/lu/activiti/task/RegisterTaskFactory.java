package com.lu.activiti.task;

import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RegisterTaskFactory {
  @Bean("user.register.phoneValidation")
  public Connector phoneNumberValidation(){
    return new ServiceTask<IActivitiTask>(new PhoneValidationTask());
  }
}
