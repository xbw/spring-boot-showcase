package com.xbw.spring.boot.framework.ws;

import org.apache.axis.transport.http.AxisServlet;

/**
 * http://host:port/services
 * need @{@link org.springframework.boot.web.servlet.ServletComponentScan}
 * @author xbw
 */
@javax.servlet.annotation.WebServlet(
        urlPatterns = "/services/*",
        loadOnStartup = 1,
        name = "AxisServlet"
)
public class WebServlet extends AxisServlet {

}