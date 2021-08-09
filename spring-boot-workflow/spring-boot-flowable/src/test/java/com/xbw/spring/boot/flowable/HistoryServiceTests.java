package com.xbw.spring.boot.flowable;

import org.flowable.engine.HistoryService;
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
                .processDefinitionKey(FlowableTests.PROCESS_DEFINITION_KEY)
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
                .processInstanceId(FlowableTests.PROCESS_INSTANCE_ID)
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
                .taskAssignee(FlowableTests.USER_ID)
                .finished()
                .includeProcessVariables()
                .orderByHistoricTaskInstanceEndTime()
                .desc()
                .list()
                .forEach(System.out::println);
    }

}
