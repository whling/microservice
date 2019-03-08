package com.whl.microservice.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component//加入到Spring容器
public class JdbcConfigBean {

	@Value("${aaa}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}