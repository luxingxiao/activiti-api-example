package com.lu.activiti.task;

import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lu.activiti.jwt.JwtParser;
import com.lu.activiti.jwt.JwtValidator;

@Component
public class TaskFactory {
	@Autowired
	private JwtValidator jwtValidator;

	@Autowired
	private JwtParser jwtParser;

	@Bean
	public Connector getHttpTask() {
		return context -> {
			return context;
		};
	}

	@Bean
	public Connector postHttpTask() {
		return context -> {
			return context;
		};
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
