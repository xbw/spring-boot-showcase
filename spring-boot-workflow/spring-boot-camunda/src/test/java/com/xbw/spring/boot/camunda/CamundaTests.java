package com.xbw.spring.boot.camunda;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xbw
 */
@SpringBootTest
class CamundaTests {
    public final static String PROCESS_DEFINITION_KEY = "twoTaskProcess";
    public final static String PROCESS_DEFINITION_ID = "twoTaskProcess:1:1d762cb4-fa7a-11eb-a3a1-8c1645f31acb";
    public final static String PROCESS_INSTANCE_ID = "507f348d-fa7a-11eb-b0fa-8c1645f31acb";
    public final static String TASK_ID = "a6ae0aeb-fa7a-11eb-abbe-8c1645f31acb";
    public final static String USER_ID = "xbw";
    public final static String ASSIGNEE = USER_ID;
    @Autowired
    IdentityService identityService;
    @Autowired
    HistoryService historyService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;

}
