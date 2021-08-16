package com.xbw.spring.boot.project.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface JaxWsService {
    @WebMethod(action = "sayHello")
    String sayHello(@WebParam(name = "text") String text);
}