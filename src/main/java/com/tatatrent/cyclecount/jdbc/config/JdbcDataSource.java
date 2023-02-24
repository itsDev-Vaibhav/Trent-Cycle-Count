package com.tatatrent.cyclecount.jdbc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.tatatrent.cyclecount.utils.AppConstants;

@Configuration
public class JdbcDataSource {
	
	@Value("${oracle.db.host}") String host;
	@Value("${oracle.db.username}") String username;
	@Value("${oracle.db.password}") String password;
	
	@Bean
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder
				.create()
				.driverClassName(AppConstants.DRIVER_CLASS)
				.username(username.trim())
				.password(password.trim())
				.url(getUrl())
				.build();
	}

	private String getUrl() {
		StringBuilder strbr = new StringBuilder(AppConstants.DB_PREFIX);
		strbr.append(host.trim()).append(":" + AppConstants.DB_POSTFIX);
		return strbr.toString();
	}

}
