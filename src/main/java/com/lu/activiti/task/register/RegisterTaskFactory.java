package com.lu.activiti.task.register;

import com.lu.activiti.task.IActivitiTask;
import com.lu.activiti.task.ServiceTask;
import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RegisterTaskFactory {

  @Bean("user.register.phoneValidation")
  public Connector phoneNumberValidation() {
    return new ServiceTask<PhoneValidationTask>(PhoneValidationTask.class);
  }

  @Bean("user.register.sendVerifyCode")
  public Connector sendVerifyCode() {
    return new ServiceTask<SendVerifyCodeTask>(SendVerifyCodeTask.class);
  }

  @Bean("user.register.verifyCodeValidation")
  public Connector verifyCodeValidation() {
    return new ServiceTask<VerifyCodeTask>(VerifyCodeTask.class);
  }

  @Bean("user.register.addUser")
  public Connector addUser() {
    return new ServiceTask<AddUserTask>(AddUserTask.class);
  }
}
