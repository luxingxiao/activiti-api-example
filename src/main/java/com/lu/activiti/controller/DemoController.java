/**
 * 
 */
package com.lu.activiti.controller;

import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/test-get-http-task")
	public void testGetHttpTask() {
		ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder.start()
				.withProcessDefinitionKey("testGetHttpTask").build());
		
	}
}
