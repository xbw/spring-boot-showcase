package com.xbw.spring.boot.ws;

import com.xbw.spring.boot.framework.util.HttpSoapUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author xbw
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WSTests {

    @Autowired
    private TestRestTemplate restTemplate;

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

    @Test
    public void jersey() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/jersey",String.class);
        Assertions.assertEquals(entity.getStatusCode(),HttpStatus.OK);
        Assertions.assertEquals(entity.getBody(),"Jersey");
    }
}
