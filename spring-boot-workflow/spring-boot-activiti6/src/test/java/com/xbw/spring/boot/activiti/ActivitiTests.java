package com.xbw.spring.boot.activiti;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.FormService;
import org.activiti.engine.ManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * https://www.activiti.org/userguide/
 * @author xbw
 */
@SpringBootTest
class ActivitiTests {
    public final static String PROCESS_DEFINITION_KEY = "oneTaskProcess";
    public final static String PROCESS_DEFINITION_ID = "oneTaskProcess:1:3";
    public final static String PROCESS_INSTANCE_ID = "2501";
    public final static String TASK_ID = "2507";
    public final static String USER_ID = "xbw";
    public final static String ASSIGNEE = USER_ID;

    @Autowired
    FormService formService;
    @Autowired
    ManagementService managementService;
    @Autowired
    DynamicBpmnService dynamicBpmnService;

    @Test
    void formService() {
        System.out.println(formService.getStartFormKey(PROCESS_DEFINITION_ID));
    }

    @Test
    void managementService() {
        managementService.getProperties().forEach((k, v) -> System.out.println(k + " = " + v));
    }

    @Test
    void dynamicBpmnService() {
        ObjectNode objectNode = dynamicBpmnService.getProcessDefinitionInfo(PROCESS_DEFINITION_ID);
        System.out.println(objectNode);
    }
}
