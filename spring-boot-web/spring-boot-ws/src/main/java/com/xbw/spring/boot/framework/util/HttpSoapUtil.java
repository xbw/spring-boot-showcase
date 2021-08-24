package com.xbw.spring.boot.framework.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author xbw
 * @see org.apache.http.HttpStatus
 * @see org.springframework.http.HttpStatus
 */
public class HttpSoapUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpSoapUtil.class);
    private static final String CONTENT_TYPE_SOAP11 = "text/xml;charset=UTF-8";
    private static final String CONTENT_TYPE_SOAP12 = "application/soap+xml;charset=UTF-8";
    private static int socketTimeout = 30 * 60;
    private static int connectTimeout = 30 * 60;

    /**
     * SOAP1.1 or SOAP1.2
     * @param url
     * @param xml
     * @param action
     * @return
     */
    public static HttpResponse getSoap11HttpResponse(String url, String xml, String action) {
        return getHttpResponse(getHttpPost(url, xml, action, CONTENT_TYPE_SOAP11));
    }

    /**
     * SOAP1.1 or SOAP1.2
     * @param url
     * @param xml
     * @param action
     * @return
     */
    public static String getSoap11(String url, String xml, String action) {
        String retVal = getString(getHttpPost(url, xml, action, CONTENT_TYPE_SOAP11));
        logger.info("getSoap11 -> {}", retVal);
        return retVal;
    }

    /**
     * SOAP1.2
     * @param url
     * @param xml
     * @param action
     * @return
     */
    public static HttpResponse getSoap12HttpResponse(String url, String xml, String action) {
        return getHttpResponse(getHttpPost(url, xml, action, CONTENT_TYPE_SOAP12));
    }

    /**
     * SOAP1.2
     * @param url
     * @param xml
     * @param action
     * @return
     */
    public static String getSoap12(String url, String xml, String action) {
        String retVal = getString(getHttpPost(url, xml, action, CONTENT_TYPE_SOAP12));
        logger.debug("getSoap12 -> {}", retVal);
        return retVal;
    }

    private static HttpPost getHttpPost(String url, String xml, String action, String contentType) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", contentType);
        httpPost.setHeader("SOAPAction", action);
        httpPost.setEntity(new StringEntity(xml, Charset.forName(StandardCharsets.UTF_8.name())));
        return httpPost;
    }

    private static HttpResponse getHttpResponse(HttpPost httpPost) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();) {
            try (CloseableHttpResponse response = httpClient.execute(httpPost);) {
                return response;
            }
        } catch (IOException e) {
            logger.error("IOException >>", e);
        }
        return null;
    }

    private static String getString(HttpPost httpPost) {
        String retVal = "";
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();) {
            try (CloseableHttpResponse response = httpClient.execute(httpPost);) {
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null) {
                    retVal = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
                }
            }
        } catch (IOException e) {
            logger.error("IOException >>", e);
        }
        return retVal;
    }

}
