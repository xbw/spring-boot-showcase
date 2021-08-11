package com.xbw.spring.boot.camunda;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RuntimeServiceTests {
    @Autowired
    IdentityService identityService;
    @Autowired
    RuntimeService runtimeService;

    @Test
    void createProcessInstanceQuery() {
        runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(CamundaTests.PROCESS_DEFINITION_KEY)
                .list()
                .forEach(System.out::println);
    }

    @Test
    void start() {
        identityService.setAuthenticatedUserId(CamundaTests.USER_ID);
        Map<String, Object> variables = new HashMap<>();
        variables.put("start", "start");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(CamundaTests.PROCESS_DEFINITION_KEY, variables);

        System.out.printf("ProcessDefinitionId: %s%n", processInstance.getProcessDefinitionId());
    }

    @Test
    public void suspendOrActiveProcessInstance() {
        String processInstanceId = CamundaTests.PROCESS_INSTANCE_ID;
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        boolean suspend = processInstance.isSuspended();
        if (suspend) {
            runtimeService.activateProcessInstanceById(processInstanceId);
            System.out.println("processInstanceId: " + processInstanceId + " activated");
        } else {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            System.out.println("processInstanceId: " + processInstanceId + " suspend");
        }
    }
}
