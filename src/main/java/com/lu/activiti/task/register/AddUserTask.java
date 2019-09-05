package com.lu.activiti.task.register;

import com.lu.activiti.annotation.Input;
import com.lu.activiti.task.IActivitiTask;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.stereotype.Service;

@Slf4j
public class AddUserTask implements IActivitiTask {

  @Input
  private String phoneNumber;

  @Override
  public void execute() {
    log.info("添加用户: {}", phoneNumber);
  }
}
