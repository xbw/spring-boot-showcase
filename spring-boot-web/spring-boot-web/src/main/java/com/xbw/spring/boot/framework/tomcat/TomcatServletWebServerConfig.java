package com.xbw.spring.boot.framework.tomcat;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.UpgradeProtocol;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.apache.coyote.ajp.AjpAprProtocol;
import org.apache.coyote.ajp.AjpNio2Protocol;
import org.apache.coyote.ajp.AjpNioProtocol;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.coyote.http11.Http11AprProtocol;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer
 * http:
 * @see org.apache.coyote.http11.Http11NioProtocol
 * @see org.apache.coyote.http11.Http11Nio2Protocol
 * @see org.apache.coyote.http11.Http11AprProtocol
 * ajp:
 * @see org.apache.coyote.ajp.AjpNioProtocol
 * @see org.apache.coyote.ajp.AjpNio2Protocol
 * @see org.apache.coyote.ajp.AjpAprProtocol
 */
@ConditionalOnClass({Tomcat.class, UpgradeProtocol.class})
@EnableConfigurationProperties(AjpProperties.class)
@Configuration(proxyBeanMethods = false)
public class TomcatServletWebServerConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AjpProperties ajp;

    @ConditionalOnProperty(name = "tomcat.http.protocol", havingValue = "nio")
    @Bean
    public WebServerFactoryCustomizer tomcatServletWebServerNio() {
        return (WebServerFactoryCustomizer<TomcatServletWebServerFactory>) factory -> {
            if (ajp.isEnabled()) {
                factory.addAdditionalTomcatConnectors(getAJPConnector(AjpNioProtocol.class));
            }
        };
    }

    @ConditionalOnProperty(name = "tomcat.http.protocol", havingValue = "nio2")
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerNio2() {
        return getTomcatServletWebServer(Http11Nio2Protocol.class, AjpNio2Protocol.class);
    }

    /**
     * https://github.com/spring-projects/spring-boot/issues/20377
     * Caused by: org.apache.catalina.LifecycleException: The configured protocol [org.apache.coyote.http11.Http11AprProtocol] requires the APR/native library which is not available
     * http://tomcat.apache.org/tomcat-8.5-doc/apr.html
     * if windows need move tcnative-1.dll to System.getProperty("java.library.path")
     * @return
     */
    @ConditionalOnProperty(name = "tomcat.http.protocol", havingValue = "apr")
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServeApr() {
        return getTomcatServletWebServer(Http11AprProtocol.class, AjpAprProtocol.class);
    }

    private TomcatServletWebServerFactory getTomcatServletWebServer(@NonNull Class<? extends AbstractHttp11Protocol> http11Protocol,
                                                                    @NonNull Class<? extends AbstractAjpProtocol> ajpProtocol) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setProtocol(http11Protocol.getName());
        logger.info("tomcat.ajp.enabled = {}", ajp.isEnabled());
        if (ajp.isEnabled()) {
            factory.addAdditionalTomcatConnectors(getAJPConnector(ajpProtocol));
        }
        if (http11Protocol.equals(Http11AprProtocol.class)) {
            factory.addContextLifecycleListeners(new AprLifecycleListener());
        }
        return factory;
    }

    private Connector getAJPConnector(@NonNull Class<? extends AbstractAjpProtocol> ajpProtocol) {
        Connector connector = null;
        try {
            AbstractAjpProtocol protocol = ajpProtocol.newInstance();
            if (ajp.isSecretRequired()) {
                // protocol.setSecretRequired(true);
                protocol.setSecret(ajp.getSecret());
            } else {
                protocol.setSecretRequired(false);
            }
            connector = new Connector(protocol);
            connector.setPort(ajp.getPort());
            // connector.setSecure(true);
        } catch (InstantiationException e) {
            logger.error("InstantiationException: ", e);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException: ", e);
        }
        return connector;
    }
}
