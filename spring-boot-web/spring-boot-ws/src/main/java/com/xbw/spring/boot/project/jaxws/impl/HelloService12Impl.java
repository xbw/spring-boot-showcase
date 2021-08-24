package com.xbw.spring.boot.project.jaxws.impl;

import com.xbw.spring.boot.project.jaxws.HelloService;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

/**
 * https://stackoverflow.com/questions/16030574/jax-ws-has-xsd-schema-in-different-url
 */
@WebService(
        serviceName = "HelloService",
        portName = "HelloServiceSoap",
        endpointInterface = "com.xbw.spring.boot.project.jaxws.HelloService",
        targetNamespace = "http://jaxws.project.boot.spring.xbw.com"/*,
        wsdlLocation = "wsdl/HelloService.wsdl"*/
)
//@BindingType(SOAPBinding.SOAP11HTTP_BINDING)
//@BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING)
//@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
@BindingType(SOAPBinding.SOAP12HTTP_MTOM_BINDING)
public class HelloService12Impl implements HelloService {

    @Override
    public String sayHello(String text) {
        return "Hello " + text;
    }
}
