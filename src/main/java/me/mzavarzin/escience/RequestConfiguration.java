package me.mzavarzin.escience;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

@Configuration
@ApplicationPath("/")
public class RequestConfiguration extends ResourceConfig {
	public RequestConfiguration() {
	}

	@PostConstruct
	public void setUp() {
		register(RequestApi.class);
        register(OpenApiResource.class);
        System.out.println("Hello RequestConf");
	}
}