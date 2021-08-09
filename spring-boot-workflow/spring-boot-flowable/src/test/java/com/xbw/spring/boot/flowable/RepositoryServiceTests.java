package com.xbw.spring.boot.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
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
                .addClasspathResource("processes/one-task-process.bpmn20.xml")
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
                .processDefinitionKey(FlowableTests.PROCESS_DEFINITION_KEY)
                .list()
                .forEach(System.out::println);
    }

    @Test
    void suspendOrActivateProcessDefinition() {
        String processDefinitionId = FlowableTests.PROCESS_DEFINITION_ID;
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
