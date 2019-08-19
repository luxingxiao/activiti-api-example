/**
 * 
 */
package com.lu.activiti.task;

import org.activiti.api.process.model.IntegrationContext;
import org.activiti.api.process.runtime.connector.Connector;

/**
 * @author luxingxiao
 *
 */
public class ServiceTask<T extends IActivitiTask> implements Connector {
	private T task;

	public ServiceTask(T task) {
		this.task = task;
	}

	@Override
	public IntegrationContext apply(IntegrationContext t) {
		task.getClass().getAnnotations();
		
		task.execute();
		return t;
	}

}
