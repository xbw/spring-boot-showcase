package com.xbw.spring.boot.project.jaxws;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author xbw
 */
@WebService
public interface HelloService {
    @WebMethod(action = "sayHello")
    String sayHello(@WebParam(name = "text") String text);
}
