package com.xbw.spring.boot.activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
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
    void start() {
        identityService.setAuthenticatedUserId(ActivitiTests.USER_ID);
        Map<String, Object> variables = new HashMap<>();
        variables.put("start", "start");
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ActivitiTests.processDefinitionKey);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ActivitiTests.PROCESS_DEFINITION_KEY, variables);

        System.out.printf("ProcessDefinitionId: %s, ProcessDefinitionKey: %s%n", processInstance.getProcessDefinitionId(), processInstance.getProcessDefinitionKey());
        //ProcessDefinitionId: oneTaskProcess:1:3, ProcessDefinitionKey: oneTaskProcess
    }

    @Test
    public void suspendOrActiveProcessInstance() {
        String processInstanceId = ActivitiTests.PROCESS_INSTANCE_ID;
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
