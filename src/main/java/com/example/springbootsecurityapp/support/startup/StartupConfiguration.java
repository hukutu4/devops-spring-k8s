package com.example.springbootsecurityapp.support.startup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.DatabaseStartupValidator;

import javax.sql.DataSource;

@Configuration
public class StartupConfiguration {
  @Bean
  public DatabaseStartupValidator databaseStartupValidator(final DataSource dataSource) {
    final DatabaseStartupValidator databaseStartupValidator = new DatabaseStartupValidator();
    databaseStartupValidator.setDataSource(dataSource);
    return databaseStartupValidator;
  }
}