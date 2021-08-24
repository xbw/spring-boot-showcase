package com.xbw.spring.boot.project.endpoint;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Component
@Produces("text/plain")
@Path("/jersey")
public class JerseyEndpoint {
    @GET
    public String message() {
        return "Jersey";
    }

    @GET
    @Path("/reverse")
    public String reverse(@QueryParam("data") @NotNull String data) {
        return new StringBuilder(data).reverse().toString();
    }
}