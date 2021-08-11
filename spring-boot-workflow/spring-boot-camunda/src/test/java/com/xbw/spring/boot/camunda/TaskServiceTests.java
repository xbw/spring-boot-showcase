package com.xbw.spring.boot.camunda;

import org.camunda.bpm.engine.TaskService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskServiceTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    TaskService taskService;

    @Test
    void createTaskQuery() {
        taskService.createTaskQuery()
                .processDefinitionKey(CamundaTests.PROCESS_DEFINITION_KEY)
                .processInstanceId(CamundaTests.PROCESS_INSTANCE_ID)
                .list()
                .forEach(System.out::println);
    }

    @Test
    void taskService() {
        logger.info("Number of tasks: {}", taskService.createTaskQuery().count());
        taskService.createTaskQuery()
                .taskAssignee(CamundaTests.ASSIGNEE)
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void setAssignee() {
        taskService.setAssignee(CamundaTests.TASK_ID, CamundaTests.USER_ID);
    }

    @Test
    void claim() {
        taskService.claim(CamundaTests.TASK_ID, CamundaTests.ASSIGNEE);
    }

    @Test
    void complete() {
        taskService.createComment(CamundaTests.TASK_ID, CamundaTests.PROCESS_INSTANCE_ID, "comment");
        Map<String, Object> variables = new HashMap<>();
        variables.put("pass",true);
        taskService.complete(CamundaTests.TASK_ID, variables);
    }
}
