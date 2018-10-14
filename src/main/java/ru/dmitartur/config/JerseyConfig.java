package ru.dmitartur.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import ru.dmitartur.controller.rest.TestRestController;

import javax.ws.rs.ApplicationPath;


@Configuration
@ApplicationPath("test")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(TestRestController.class);
    }
}
