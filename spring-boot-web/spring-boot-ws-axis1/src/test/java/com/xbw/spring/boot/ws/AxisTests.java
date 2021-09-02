package com.xbw.spring.boot.ws;

import com.xbw.spring.boot.project.pojo.Weather;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPConstants;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;

/**
 * @author xbw
 */
@SuppressWarnings("unchecked")
class AxisTests {
    final static String HELLO_SERVICE = "http://localhost:8080/services/HelloService";
    final static String WEATHER_SERVICE = "http://localhost:8080/services/WeatherService";

    @Test
    void client() throws ServiceException, RemoteException {
        Call call = (Call) new Service().createCall();
        call.setTargetEndpointAddress(HELLO_SERVICE);
        call.setOperationName(new QName("sayHello"));
        call.setUseSOAPAction(true);
        Object result = call.invoke(new Object[]{"Client"});
        Assertions.assertEquals("Hello Client", result);
    }

    @Test
    void http() throws IOException {
        HttpPost httpPost = new HttpPost(HELLO_SERVICE);
        httpPost.setHeader("Content-Type", MediaType.TEXT_XML_VALUE);
        httpPost.setHeader("Content-Type", org.springframework.http.MediaType.TEXT_XML_VALUE);
        httpPost.setHeader("Content-Type", SOAPConstants.SOAP_1_1_CONTENT_TYPE);
        httpPost.setHeader("SOAPAction", "");

        String xml = "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:axis=\"http://axis.project.boot.spring.xbw.com\">" +
                "   <soapenv:Header/>" +
                "   <soapenv:Body>" +
                "      <axis:sayHello soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">" +
                "         <text xsi:type=\"xsd:string\" xs:type=\"type:string\" xmlns:xs=\"http://www.w3.org/2000/XMLSchema-instance\">HTTP</text>" +
                "      </axis:sayHello>" +
                "   </soapenv:Body>" +
                "</soapenv:Envelope>";
        execute(httpPost, xml);
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

    @Test
    void setWeather() throws ServiceException, RemoteException {

        Call call = getWeatherCall(true);

        // Setting the weather
        QName opSetWeather = new QName("http://pojo.project.boot.spring.xbw.com", "setWeather");

        Weather weather = new Weather();
        weather.setTemperature((float) 39.3);
        weather.setForecast("Cloudy with showers");
        weather.setRain(true);
        weather.setHowMuchRain((float) 4.5);

        Weather[] opSetWeatherArgs = new Weather[]{weather};
        call.setOperationName(opSetWeather);
        call.setUseSOAPAction(true);
        call.invoke(opSetWeatherArgs);
    }

    @Test
    void getWeather() throws ServiceException, RemoteException {

        Call call = getWeatherCall(false);

        // Getting the weather
        QName opGetWeather = new QName("http://pojo.project.boot.spring.xbw.com", "getWeather");
        Object[] opGetWeatherArgs = new Object[]{};
        call.setOperationName(opGetWeather);
        call.setUseSOAPAction(true);
        Weather result = (Weather) call.invoke(opGetWeatherArgs);

        // Displaying the result
        System.out.println("Temperature               : " + result.getTemperature());
        System.out.println("Forecast                  : " + result.getForecast());
        System.out.println("Rain                      : " + result.getRain());
        System.out.println("How much rain (in inches) : " + result.getHowMuchRain());
    }

    private Call getWeatherCall(boolean set) throws ServiceException {
        Call call = (Call) new Service().createCall();
        call.setTargetEndpointAddress(WEATHER_SERVICE);

        QName qn = new QName("urn:WeatherService", "Weather");
        BeanSerializerFactory bsf = new BeanSerializerFactory(Weather.class, qn);
        BeanDeserializerFactory bdf = new BeanDeserializerFactory(Weather.class, qn);
        call.registerTypeMapping(Weather.class, qn, bsf, bdf);

        if (set){
            call.addParameter("arg1", qn, ParameterMode.IN);
        }
        call.setReturnType(new QName("urn:WeatherService", "Weather"));
        return call;
    }

}
