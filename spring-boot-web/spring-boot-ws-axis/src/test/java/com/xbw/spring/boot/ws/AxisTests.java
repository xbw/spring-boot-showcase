package com.xbw.spring.boot.ws;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.xbw.spring.boot.project.pojo.Weather;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xbw
 */
@SuppressWarnings("unchecked")
class AxisTests {
    final static String HELLO_SERVICE = "http://localhost:8080/services/HelloService";
    final static String WEATHER_SERVICE = "http://localhost:8080/services/WeatherService";

    @Test
    void helloHTTP() throws IOException {
        HttpPost httpPost = new HttpPost(HELLO_SERVICE);
        httpPost.setHeader("Content-Type", MediaType.TEXT_XML);
        httpPost.setHeader("Content-Type", org.springframework.http.MediaType.TEXT_XML_VALUE);
        httpPost.setHeader("Content-Type", SOAPConstants.SOAP_1_1_CONTENT_TYPE);
        httpPost.setHeader("SOAPAction", "urn:sayHello");

        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:axis=\"http://axis.project.boot.spring.xbw.com\">" +
                "   <soapenv:Header/>" +
                "   <soapenv:Body>" +
                "      <axis:hello>" +
                "         <text>HTTP</text>" +
                "      </axis:hello>" +
                "   </soapenv:Body>" +
                "</soapenv:Envelope>";
        execute(httpPost, xml);
    }

    @Test
    void helloRPC() throws AxisFault {
        EndpointReference targetEPR = new EndpointReference(HELLO_SERVICE);
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        options.setTo(targetEPR);
        QName opSayHello = new QName("http://axis.project.boot.spring.xbw.com", "sayHello");
        Object[] opSayHelloArgs = new Object[]{"RPC"};
        Class<String>[] returnTypes = new Class[]{String.class};
        Object[] result = serviceClient.invokeBlocking(opSayHello, opSayHelloArgs, returnTypes);
        Arrays.stream(result).forEach(System.out::println);
    }

    @Test
    void helloRest() throws AxisFault {
        EndpointReference targetEPR = new EndpointReference(HELLO_SERVICE);
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        options.setTo(targetEPR);
        options.setProperty(Constants.Configuration.ENABLE_REST, Constants.VALUE_TRUE);
        QName opSayHello = new QName("http://axis.project.boot.spring.xbw.com", "sayHello");
        Object[] opSayHelloArgs = new Object[]{"REST"};
        Class<String>[] returnTypes = new Class[]{String.class};
        Object[] result = serviceClient.invokeBlocking(opSayHello, opSayHelloArgs, returnTypes);
        Arrays.stream(result).forEach(System.out::println);
    }

    @Test
    void helloJSON() throws IOException {
        HttpPost httpPost = new HttpPost(HELLO_SERVICE);
        httpPost.setHeader("Content-Type", MediaType.APPLICATION_JSON);
        //{"response":"Hello JSON"}
        execute(httpPost, "{\"sayHello\":[{\"text\":\"JSON\"}]}");
    }

    @Test
    void weatherRPC() throws AxisFault {
        EndpointReference targetEPR = new EndpointReference(WEATHER_SERVICE);
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        options.setTo(targetEPR);

        // Setting the weather
        QName opSetWeather = new QName("http://axis.project.boot.spring.xbw.com", "setWeather");
        Weather weather = createWeather();
        Weather[] opSetWeatherArgs = new Weather[]{weather};
        serviceClient.invokeRobust(opSetWeather, opSetWeatherArgs);

        // Getting the weather
        QName opGetWeather = new QName("http://axis.project.boot.spring.xbw.com", "getWeather");
        Object[] opGetWeatherArgs = new Object[]{};
        Class<String>[] returnTypes = new Class[]{Weather.class};
        Object[] response = serviceClient.invokeBlocking(opGetWeather, opGetWeatherArgs, returnTypes);
        Weather result = (Weather) response[0];

        // Displaying the result
        System.out.println("Temperature               : " + result.getTemperature());
        System.out.println("Forecast                  : " + result.getForecast());
        System.out.println("Rain                      : " + result.getRain());
        System.out.println("How much rain (in inches) : " + result.getHowMuchRain());
    }

    @Test
    void weatherJSON() throws IOException {
        HttpPost httpPost = new HttpPost(WEATHER_SERVICE);
        httpPost.setHeader("Content-Type", MediaType.APPLICATION_JSON);

        Weather weather = createWeather();
        Map<String, Weather> tmp = new HashMap<>();
        tmp.put("weather", weather);
        Map<String, Map<String, Weather>[]> map = new HashMap<>();
        map.put("setWeather", new Map[]{tmp});

        httpPost.setHeader("SOAPAction", "urn:setWeather");
        //{"setWeather":[{"weather":{"temperature":39.3,"forecast":"Cloudy with showers","rain":true,"howMuchRain":4.5}}]}
        execute(httpPost, new JsonMapper().writeValueAsString(map));

        httpPost.setHeader("SOAPAction", "urn:getWeather");
        //{"response":{"forecast":"Cloudy with showers","howMuchRain":4.5,"rain":true,"temperature":39.3}}
        execute(httpPost, "{\"getWeather\":[]}");
    }

    private void execute(HttpPost httpPost, String entity) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30 * 60)
                .setConnectTimeout(30 * 60)
                .build();
        httpPost.setConfig(requestConfig);

        httpPost.setEntity(new StringEntity(entity, Charset.forName(StandardCharsets.UTF_8.name())));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            System.out.println(EntityUtils.toString(httpEntity, StandardCharsets.UTF_8));
        }
    }

    private Weather createWeather() {
        Weather weather = new Weather();
        weather.setTemperature((float) 39.3);
        weather.setForecast("Cloudy with showers");
        weather.setRain(true);
        weather.setHowMuchRain((float) 4.5);
        return weather;
    }
}
