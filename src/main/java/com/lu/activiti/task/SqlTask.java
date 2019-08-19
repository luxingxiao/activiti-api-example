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
public class SqlTask implements Connector {

	@Override
	public IntegrationContext apply(IntegrationContext t) {
		t.getInBoundVariables();
		return null;
	}

}
