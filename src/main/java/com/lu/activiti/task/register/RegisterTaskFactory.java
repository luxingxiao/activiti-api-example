package com.lu.activiti.task.register;

import com.lu.activiti.task.IActivitiTask;
import com.lu.activiti.task.ServiceTask;
import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RegisterTaskFactory {

  private PhoneValidationTask phoneValidationTask;
  private SendVerifyCodeTask sendVerifyCodeTask;
  private VerifyCodeTask verifyCodeTask;
  private AddUserTask addUserTask;

  public RegisterTaskFactory(@Autowired PhoneValidationTask phoneValidationTask,
      @Autowired SendVerifyCodeTask sendVerifyCodeTask, VerifyCodeTask verifyCodeTask,
      AddUserTask addUserTask) {
    this.phoneValidationTask = phoneValidationTask;
    this.sendVerifyCodeTask = sendVerifyCodeTask;
    this.verifyCodeTask = verifyCodeTask;
    this.addUserTask = addUserTask;
  }

  @Bean("user.register.phoneValidation")
  public Connector phoneNumberValidation() {
    return new ServiceTask<IActivitiTask>(phoneValidationTask);
  }

  @Bean("user.register.sendVerifyCode")
  public Connector sendVerifyCode() {
    return new ServiceTask<IActivitiTask>(sendVerifyCodeTask);
  }

  @Bean("user.register.verifyCodeValidation")
  public Connector verifyCodeValidation() {
    return new ServiceTask<IActivitiTask>(verifyCodeTask);
  }

  @Bean("user.register.addUser")
  public Connector addUser() {
    return new ServiceTask<IActivitiTask>(addUserTask);
  }
}
