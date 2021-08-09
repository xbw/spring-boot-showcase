package com.xbw.spring.boot.flowable;

import org.flowable.engine.TaskService;
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
    void taskService() {
        logger.info("Number of tasks: {}", taskService.createTaskQuery().count());
        taskService.createTaskQuery()
//                .taskAssignee(FlowableTests.ASSIGNEE)
                .list()
                .forEach(System.out::println);
    }

    @Test
    public void setAssignee() {
        taskService.setAssignee(FlowableTests.TASK_ID, FlowableTests.USER_ID);
    }

    @Test
    void claim() {
        taskService.claim(FlowableTests.TASK_ID, FlowableTests.ASSIGNEE);
    }

    @Test
    void complete() {
        taskService.addComment(FlowableTests.TASK_ID, FlowableTests.PROCESS_INSTANCE_ID, "comment");
        Map<String, Object> variables = new HashMap<>();
        variables.put("complete", "complete");
        taskService.complete(FlowableTests.TASK_ID, variables);
    }
}
