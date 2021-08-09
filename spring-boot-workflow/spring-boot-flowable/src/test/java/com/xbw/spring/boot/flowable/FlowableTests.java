package com.xbw.spring.boot.flowable;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.FormService;
import org.flowable.engine.ManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xbw
 */
@SpringBootTest
class FlowableTests {
    public final static String PROCESS_DEFINITION_KEY = "oneTaskProcess";
    public final static String PROCESS_DEFINITION_ID = "oneTaskProcess:1:9fb4c762-f91b-11eb-bab9-8ca9826575ee";
    public final static String PROCESS_INSTANCE_ID = "a02e1613-f91b-11eb-bab9-8ca9826575ee";
    public final static String TASK_ID = "a038012a-f91b-11eb-bab9-8ca9826575ee";
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
