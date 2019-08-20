/**
 * 
 */
package com.lu.activiti.task;

import com.lu.activiti.annotation.Input;
import com.lu.activiti.annotation.Output;

/**
 * @author luxingxiao
 *
 */
public class PostHttpTask implements IActivitiTask {
	@Input
	private String url;
	@Input
	private String body;
	@Output
	private String responseBody;

	@Override
	public void execute() {

	}

}
