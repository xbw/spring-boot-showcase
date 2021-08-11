package com.xbw.spring.boot.flowable;

import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
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
        identityService.setAuthenticatedUserId(FlowableTests.USER_ID);
        Map<String, Object> variables = new HashMap<>();
        variables.put("start", "start");
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ActivitiTests.processDefinitionKey);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(FlowableTests.PROCESS_DEFINITION_KEY, variables);

        System.out.printf("ProcessDefinitionId: %s, ProcessDefinitionKey: %s%n", processInstance.getProcessDefinitionId(), processInstance.getProcessDefinitionKey());
        //ProcessDefinitionId: oneTaskProcess:1:5003, ProcessDefinitionKey: oneTaskProcess
    }

    @Test
    public void suspendOrActiveProcessInstance() {
        String processInstanceId = FlowableTests.PROCESS_INSTANCE_ID;
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
