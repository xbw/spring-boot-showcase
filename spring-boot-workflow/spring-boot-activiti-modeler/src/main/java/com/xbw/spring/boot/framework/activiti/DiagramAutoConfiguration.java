package com.xbw.spring.boot.framework.activiti;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(name = {"org.activiti.rest.diagram.services.ProcessDefinitionDiagramLayoutResource", "org.springframework.web.servlet.DispatcherServlet"})
public class DiagramAutoConfiguration {

    @Configuration
    @ComponentScan({"org.activiti.rest.diagram.services"})
    public static class ComponentDiagramResourcesConfiguration {

        // The component scan cannot be on the root configuration, it would trigger
        // always even if the condition is evaluating to false.
        // Hence, this 'dummy' configuration

    }

}
