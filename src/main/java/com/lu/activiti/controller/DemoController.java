/**
 *
 */
package com.lu.activiti.controller;

import com.lu.activiti.mock.RedisMock;
import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luxingxiao
 *
 */
@RestController
@RequestMapping("/api")
public class DemoController {

  @Autowired
  private ProcessRuntime processRuntime;

  @Autowired
  private TaskService taskService;

  @Autowired
  private RedisMock redis;

  @GetMapping("/test-get-http-task")
  public void testGetHttpTask() {
    ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder.start()
        .withProcessDefinitionKey("testGetHttpTask").build());

  }

  @PostMapping("/register")
  public void register(@RequestBody RegisterInfo registerInfo) {
    ProcessInstance processInstance = processRuntime
        .start(ProcessPayloadBuilder.start().withProcessDefinitionKey("process-4bba2254-7480-4826-bbf7-b954138c6e48")
            .withVariable("countryCode", registerInfo.getCountryCode())
            .withVariable("nationalNumber", registerInfo.getNationalNumber()).build());
    String phoneNumber = "" + registerInfo.getCountryCode() + registerInfo.getNationalNumber();
    redis.addProcessKey(phoneNumber, processInstance.getId());
  }

  @PostMapping("/verifyCode")
  public void verifyCode(@RequestBody VerifyCodeInfo verifyCodeInfo) {
    String phoneNumber = "" + verifyCodeInfo.getCountryCode() + verifyCodeInfo.getNationalNumber();
    TaskQuery query = taskService.createTaskQuery().processInstanceId((String) redis.getProcessKey(phoneNumber)).taskDefinitionKey("UserTask_1ox3xof");
    Task task = query.list().get(0);
    Map<String, Object> taskVariables = new HashMap<>();
    taskVariables.put("countryCode", verifyCodeInfo.getCountryCode());
    taskVariables.put("nationalNumber", verifyCodeInfo.getNationalNumber());
    taskVariables.put("verifyCode", verifyCodeInfo.getVerifyCode());
    taskService.complete(task.getId(), taskVariables);
  }

  @Data
  public static class VerifyCodeInfo {

    private int countryCode;
    private long nationalNumber;
    @NotBlank
    private String verifyCode;
  }

  @Data
  public static class RegisterInfo {

    private int countryCode;
    private long nationalNumber;
  }
}
