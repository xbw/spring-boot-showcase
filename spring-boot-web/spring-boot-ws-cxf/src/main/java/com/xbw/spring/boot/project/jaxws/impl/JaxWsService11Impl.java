package com.xbw.spring.boot.project.jaxws.impl;

import com.xbw.spring.boot.project.jaxws.JaxWsService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@Service
@WebService(
        serviceName = "JaxWsService",
        portName = "JaxWsServiceSoap",
        endpointInterface = "com.xbw.spring.boot.project.jaxws.JaxWsService",
        targetNamespace = "http://jaxws.project.boot.spring.xbw.com"/*,
        wsdlLocation = "wsdl/JaxWsService.wsdl"*/
)
@BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING)
public class JaxWsService11Impl implements JaxWsService {

    @Override
    public String sayHello(String text) {
        return "Hello " + text;
    }
}
