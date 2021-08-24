package com.xbw.spring.boot.ws;

import com.xbw.spring.boot.JaxWsServerMain;
import com.xbw.spring.boot.framework.util.HttpSoapUtil;
import com.xbw.spring.boot.project.jaxws.HelloService;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xbw
 */
public class JaxWsClientTests {

    /**
     * wsimport in JDK/bin
     * wsimport -keep -s src -p com.xbw.spring.boot.project.client.hello -verbose http://localhost:8080/services/hello-service
     */
//    @Test
//    void local() {
//        com.xbw.spring.boot.project.jaxws.client.HelloService helloService = new com.xbw.spring.boot.project.jaxws.client.HelloService_Service().getHelloServiceSoap();
//        Assertions.assertEquals("Hello xbw!", helloService.sayHello("xbw!"));
//    }

    @Test
    void localService() throws MalformedURLException {
        QName qName = new QName("http://jaxws.project.boot.spring.xbw.com", "HelloService");
        URL url = new URL(JaxWsServerMain.HELLO_SERVICE + "?wsdl");
        Service service = Service.create(url, qName);
        HelloService helloService = service.getPort(HelloService.class);
        System.out.println(helloService.sayHello("xbw"));
    }

    @Test
    void localHTTP() {
        String url = JaxWsServerMain.HELLO_SERVICE;
        StringBuilder xml = new StringBuilder();
        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:jax=\"http://jaxws.project.boot.spring.xbw.com/\">");
        xml.append("   <soapenv:Header/>");
        xml.append("   <soapenv:Body>");
        xml.append("      <jax:hello>");
        xml.append("         <text>xbw</text>");
        xml.append("      </jax:hello>");
        xml.append("   </soapenv:Body>");
        xml.append("</soapenv:Envelope>");
        HttpSoapUtil.getSoap11(url, xml.toString(), "");
    }


    @Test
    void getRegionCountry() {
        String url = "http://www.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl";
        StringBuilder xml = new StringBuilder();
        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://WebXml.com.cn/\">");
        xml.append("   <soapenv:Header/>");
        xml.append("   <soapenv:Body>");
        xml.append("      <web:getRegionCountry/>");
        xml.append("   </soapenv:Body>");
        xml.append("</soapenv:Envelope>");
        HttpSoapUtil.getSoap11(url, xml.toString(), "http://WebXml.com.cn/getRegionCountry");
    }

    @Test
    void getRegionCountry12() {
        String url = "http://www.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl";
        StringBuilder xml = new StringBuilder();
        xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:web=\"http://WebXml.com.cn/\">");
        xml.append("   <soap:Header/>");
        xml.append("   <soap:Body>");
        xml.append("      <web:getRegionCountry/>");
        xml.append("   </soap:Body>");
        xml.append("</soap:Envelope>");
        HttpSoapUtil.getSoap12(url, xml.toString(), "http://WebXml.com.cn/getRegionCountry");
    }
}
