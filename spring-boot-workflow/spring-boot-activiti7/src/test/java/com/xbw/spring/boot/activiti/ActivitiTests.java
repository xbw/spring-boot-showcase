package com.xbw.spring.boot.activiti;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.impl.db.DbSchemaCreate;
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
    public final static String PROCESS_DEFINITION_ID = "oneTaskProcess:1:20550ab1-f8e7-11eb-9517-8c1645f31acb";
    public final static String PROCESS_INSTANCE_ID = "3449fdc7-f8e7-11eb-b0c0-8c1645f31acb";
    public final static String TASK_ID = "3455bd9a-f8e7-11eb-b0c0-8c1645f31acb";
    public final static String USER_ID = "xbw";
    public final static String ASSIGNEE = USER_ID;

    @Autowired
    ManagementService managementService;
    @Autowired
    DynamicBpmnService dynamicBpmnService;

    @Test
    void name() {
        DbSchemaCreate.main(new String[]{});
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
