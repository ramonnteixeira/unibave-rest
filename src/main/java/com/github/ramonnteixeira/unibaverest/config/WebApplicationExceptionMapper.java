package com.github.ramonnteixeira.unibaverest.config;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Provider
@Component
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Inject
    private Environment env;
    
    @Override
    public Response toResponse(WebApplicationException e) {
        Map<String, Object> message = new HashMap<>();
        message.put("message", "Objeto não encontrado");
        
        if (env.acceptsProfiles("dev")) {
            message.put("developMessage", e.getMessage());
        }
        
        return Response
                  .fromResponse(e.getResponse())
                  .entity(message)
                  .build();
    }

}
