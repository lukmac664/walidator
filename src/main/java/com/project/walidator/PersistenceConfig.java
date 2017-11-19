package com.project.walidator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Łukasz Macuga on 19.11.2017.
 */
@EnableJpaRepositories(basePackages = "com.project.walidator")
@EnableAutoConfiguration
public class PersistenceConfig {
}
