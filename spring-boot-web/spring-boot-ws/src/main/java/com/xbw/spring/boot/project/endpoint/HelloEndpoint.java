/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xbw.spring.boot.project.endpoint;

import com.xbw.spring.boot.project.jaxws.HelloService;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class HelloEndpoint {

    private static final String NAMESPACE_URI = "http://jaxws.project.boot.spring.xbw.com";
    @Autowired
    private HelloService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sayHello")
    @ResponsePayload
    public String service(@RequestPayload Element request) {
        List<Element> list = request.getContent(Filters.element("serviceCode"));
        String serviceCode = list.get(0).getContent(0).getValue();
        list = request.getContent(Filters.element("reqXml"));
        String reqXml = list.get(0).getContent(0).getValue();
        System.out.println(service.sayHello(reqXml));
        return "";
    }
}
