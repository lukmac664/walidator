package com.project.walidator;

import com.project.walidator.ws.impl.ValidationServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ws.config.annotation.EnableWs;

import javax.xml.ws.Endpoint;


@EnableJpaRepositories(basePackages = "com.project.walidator")
@EnableAutoConfiguration
@EnableWs
@Configuration
public class WalidatorConfig {
    @Autowired
    private Bus bus;

    @Bean
    public Endpoint endpoint(ValidationServiceImpl service) {
        EndpointImpl endpoint = new EndpointImpl(bus, service);
        endpoint.publish("/validation");
        return endpoint;
    }
}
