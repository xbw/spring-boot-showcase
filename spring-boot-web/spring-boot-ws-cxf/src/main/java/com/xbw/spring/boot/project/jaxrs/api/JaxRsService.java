package com.xbw.spring.boot.project.jaxrs.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

@Path("/sayHello")
@Service
public interface JaxRsService {

    @GET
    @Path("/{text}")
    @Produces(MediaType.TEXT_PLAIN)
    String sayHello(@PathParam("text") String text);

}