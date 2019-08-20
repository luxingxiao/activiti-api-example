package com.lu.activiti.task;

import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TaskFactory {

	@Bean
	public Connector getHttpTask() {
		return new ServiceTask<IActivitiTask>(new GetHttpTask());
	}

	@Bean
	public Connector postHttpTask() {
		return new ServiceTask<IActivitiTask>(new PostHttpTask());
	}

	@Bean
	public Connector selectSqlTask() {
		return context -> {
			return context;
		};
	}

	@Bean
	public Connector insertSqlTask() {
		return context -> {
			return context;
		};
	}

	@Bean
	public Connector jwtTask() {
		return context -> {

			return context;
		};
	}
}
