/**
 * 
 */
package com.lu.activiti.task;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.activiti.api.process.model.IntegrationContext;
import org.activiti.api.process.runtime.connector.Connector;

import com.lu.activiti.annotation.Input;
import com.lu.activiti.annotation.Output;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luxingxiao
 *
 */
@Slf4j
public class ServiceTask<T extends IActivitiTask> implements Connector {
	private T task;

	public ServiceTask(T task) {
		this.task = task;
	}

	@Override
	public IntegrationContext apply(IntegrationContext t) {
		Set<Field> inputFields = new HashSet<>();
		Set<Field> outputFields = new HashSet<>();

		Field[] fields = task.getClass().getDeclaredFields();
		for (Field field : fields) {
			Input input = field.getAnnotation(Input.class);
			Output output = field.getAnnotation(Output.class);
			if (input != null) {
				inputFields.add(field);
			}
			if (output != null) {
				outputFields.add(field);
			}
		}
		for (Field field : inputFields) {
			try {
				field.setAccessible(true);
				field.set(task, t.getInBoundVariables().get(field.getName()));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				log.error("Set field value error.", e);
			}
		}
		task.execute();
		for (Field field : outputFields) {
			try {
				field.setAccessible(true);
				t.addOutBoundVariable(field.getName(), field.get(task));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				log.error("Get field value error.", e);
			}
		}
		return t;
	}

}
