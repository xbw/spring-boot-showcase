package com.xbw.spring.boot.camunda;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinitionQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RepositoryServiceTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RepositoryService repositoryService;

    @Test
    void deploy() {
        repositoryService.createDeployment()
                .addClasspathResource("processes/two-task-process.bpmn")
                .deploy();

        logger.info("Number of process definitions: {}",
                repositoryService
                        .createProcessDefinitionQuery()
                        .count());
    }

    @Test
    void repositoryService() {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        logger.info("Number of process definitions: {}", processDefinitionQuery.count());

        processDefinitionQuery
                .processDefinitionKey(CamundaTests.PROCESS_DEFINITION_KEY)
                .list()
                .forEach(System.out::println);
    }

    @Test
    void suspendOrActivateProcessDefinition() {
        String processDefinitionId = CamundaTests.PROCESS_DEFINITION_ID;
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId)
                .singleResult();
        if (processDefinition.isSuspended()) {
            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
            System.out.println("processDefinitionId: " + processDefinitionId + " activated");
        } else {
            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
            System.out.println("processDefinitionId: " + processDefinitionId + " suspend");
        }
    }

}
