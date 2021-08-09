package com.xbw.spring.boot.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * https://www.activiti.org/userguide/#history
 * @author xbw
 * @see HistoricProcessInstance s containing information about current and past process instances.
 * @see HistoricVariableInstance s containing the latest value of a process variable or task variable.
 * @see HistoricActivityInstance s containing information about a single execution of an activity (node in the process).
 * @see HistoricTaskInstance s containing information about current and past (completed and deleted) task instances.
 * @see HistoricDetail s containing various kinds of information related to either a historic process instances, an activity instance or a task instance.
 */
@SpringBootTest
public class HistoryServiceTests {
    @Autowired
    HistoryService historyService;

    @Test
    void createHistoricProcessInstanceQuery() {
        historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(ActivitiTests.PROCESS_DEFINITION_KEY)
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
                .processInstanceId(ActivitiTests.PROCESS_INSTANCE_ID)
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
                .taskAssignee(ActivitiTests.USER_ID)
                .finished()
                .includeProcessVariables()
                .orderByHistoricTaskInstanceEndTime()
                .desc()
                .list()
                .forEach(System.out::println);
    }

}
