package com.xbw.spring.boot.project.jaxws.impl;

import com.xbw.spring.boot.project.jaxws.HelloService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService(
        serviceName = "HelloService",
        portName = "HelloServiceSoap",
        endpointInterface = "com.xbw.spring.boot.project.jaxws.HelloService",
        targetNamespace = "http://jaxws.project.boot.spring.xbw.com"/*,
        wsdlLocation = "wsdl/HelloService.wsdl"*/
)
//@BindingType(SOAPBinding.SOAP11HTTP_BINDING)
@BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING)
//@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
//@BindingType(SOAPBinding.SOAP12HTTP_MTOM_BINDING)
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String text) {
        return "Hello " + text;
    }
}
