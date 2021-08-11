package com.xbw.spring.boot.camunda;

import org.camunda.bpm.engine.HistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HistoryServiceTests {
    @Autowired
    HistoryService historyService;

    @Test
    void createHistoricProcessInstanceQuery() {
        historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(CamundaTests.PROCESS_DEFINITION_KEY)
                .list()
                .forEach(System.out::println);
    }

    @Test
    void createHistoricVariableInstanceQuery() {
        historyService.createHistoricVariableInstanceQuery()
                .list()
                .forEach(System.out::println);
    }

    @Test
    void createHistoricActivityInstanceQuery() {
        historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(CamundaTests.PROCESS_INSTANCE_ID)
                .orderByHistoricActivityInstanceStartTime().asc()
                .orderByHistoricActivityInstanceEndTime().asc()
                .list()
                .forEach(System.out::println);
    }

    @Test
    void createHistoricDetailQuery() {
        historyService.createHistoricDetailQuery()
                .list()
                .forEach(System.out::println);
    }

    @Test
    void createHistoricTaskInstanceQuery() {
        historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(CamundaTests.USER_ID)
                .finished()
                .orderByHistoricTaskInstanceEndTime()
                .desc()
                .list()
                .forEach(System.out::println);
    }

}
