package com.xbw.spring.boot.ws;

import com.xbw.spring.boot.framework.util.HttpSoapUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xbw
 */
@SpringBootTest
public class WSTests {

    @Test
    void sayHello() {
        String url = "http://localhost:8080/services/hello";
        StringBuilder xml = new StringBuilder();
        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:jax=\"http://jaxws.project.boot.spring.xbw.com/\">");
        xml.append("   <soapenv:Header/>");
        xml.append("   <soapenv:Body>");
        xml.append("      <jax:hello>");
        xml.append("         <text>xbw</text>");
        xml.append("      </jax:hello>");
        xml.append("   </soapenv:Body>");
        xml.append("</soapenv:Envelope>");
        HttpSoapUtil.getSoap11(url, xml.toString(), "sayHello");
    }

    @Test
    void getCountryRequest() {
        String url = "http://localhost:8080/services/ws/countries";
        StringBuilder xml = new StringBuilder();
        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sch=\"http://boot.spring.xbw.com/project/services/country\">");
        xml.append("   <soapenv:Header/>");
        xml.append("   <soapenv:Body>");
        xml.append("      <sch:getCountryRequest>");
        xml.append("         <sch:name>Spain</sch:name>");
        xml.append("      </sch:getCountryRequest>");
        xml.append("   </soapenv:Body>");
        xml.append("</soapenv:Envelope>");
        HttpSoapUtil.getSoap11(url, xml.toString(), "sch:getCountryRequest");
    }
}
