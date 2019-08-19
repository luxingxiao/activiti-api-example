/**
 * 
 */
package com.lu.activiti.task;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.lu.activiti.annotation.Input;
import com.lu.activiti.annotation.Output;

import lombok.Data;

/**
 * @author luxingxiao
 *
 */
@Data
public class GetHttpTask implements IActivitiTask {
	@Input
	private String url;

	@Input
	private Map<String, String> uriVariables;

	@Output
	private String outputJson;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void execute() {
		outputJson = restTemplate.getForObject(url, String.class, uriVariables);
	}
}
