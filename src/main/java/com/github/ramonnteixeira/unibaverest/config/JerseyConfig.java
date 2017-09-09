package com.github.ramonnteixeira.unibaverest.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

import com.github.ramonnteixeira.unibaverest.resource.AlunoResourceImpl;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(AlunoResourceImpl.class);
        register(WebApplicationExceptionMapper.class);
    }

}
