package com.project.walidator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.project.walidator")
@EnableAutoConfiguration
public class PersistenceConfig {
}
