package com.github.ramonnteixeira.unibaverest.config;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.ramonnteixeira.unibaverest.resource.AlunoResource;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(WebApplicationExceptionMapper.class);
        register(ApiListingResource.class);
        register(AlunoResource.class);
    }

    @Bean
    public BeanConfig swagger2Feature() {
        final BeanConfig swagger = new BeanConfig();
        swagger.setTitle("API");
        swagger.setVersion("1.0");
        swagger.setSchemes(new String[]{"http"});
        swagger.setHost("localhost:8080");
        swagger.setBasePath("/api");
        swagger.setResourcePackage("com.github.ramonnteixeira.unibaverest.resource");
        swagger.setPrettyPrint(true);
        swagger.setScan(true);
        return swagger;
    }

}