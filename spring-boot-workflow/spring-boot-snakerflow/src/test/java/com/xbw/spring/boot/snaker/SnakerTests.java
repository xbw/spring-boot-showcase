package com.xbw.spring.boot.snaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snaker.engine.IProcessService;
import org.snaker.engine.IQueryService;
import org.snaker.engine.ITaskService;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.*;
import org.snaker.engine.entity.Process;
import org.snaker.engine.helper.StreamHelper;
import org.snaker.engine.spring.SpringSnakerEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xbw
 */
@SpringBootTest
class SnakerTests {
    final String APPLY_OPERATOR = "snaker";
    final String APPROVAL_OPERATOR = "xbw";
    final Map<String, Object> params = new HashMap<>();
    @Autowired
    private SpringSnakerEngine engine;
    @Autowired
    private IProcessService processService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IQueryService queryService;

    @BeforeEach
    void setUp() {
        params.put("apply.operator", APPLY_OPERATOR);
    }

    @Test
    void deploy() {
        System.out.printf("wf_process_id: %s%n",
                engine.process().deploy(StreamHelper.getStreamFromClasspath("flows/leave.snaker")));
        System.out.printf("wf_process_id: %s%n",
                processService.deploy(StreamHelper.getStreamFromClasspath("flows/borrow.snaker")));
        System.out.printf("wf_process_id: %s%n",
                processService.deploy(StreamHelper.getStreamFromClasspath("flows/borrow.snaker")));
    }

    @Test
    void startInstanceById() {
        params.put("approveDept.operator", "xbw");
        params.put("approveBoss.operator", "xbw");
        List<Process> process = processService.getProcesss(new QueryFilter().setName("leave"));
        process.forEach(p -> {
            Order order = engine.startInstanceById(p.getId(), APPLY_OPERATOR, params);
            System.out.println(order);
        });
    }

    @Test
    void startInstanceByName() {
        params.put("approval.operator", APPROVAL_OPERATOR);
        Order order = engine.startInstanceByName("borrow", 1, APPLY_OPERATOR, params);
        System.out.println(order);
    }

    @Test
    void startAndExecute() {
        params.put("approval.operator", APPROVAL_OPERATOR);
        Order order = engine.startInstanceByName("borrow", 1, APPLY_OPERATOR, params);
        System.out.println(order);
        List<Task> tasks = engine.query().getActiveTasks(new QueryFilter().setOrderId(order.getId()));
        List<Task> newTasks = new ArrayList<Task>();
        if (tasks != null && tasks.size() > 0) {
            Task task = tasks.get(0);
            newTasks.addAll(engine.executeTask(task.getId(), APPLY_OPERATOR, params));
        }
    }

    @Test
    void complete() {
        List<Order> orders = queryService.getActiveOrders(new QueryFilter());
        orders.forEach(order -> {
            System.out.println(order);
            List<Task> tasks = queryService.getActiveTasks(new QueryFilter().setOrderId(order.getId()));
            tasks.forEach(task -> {
                System.out.println(task);
                // taskService.complete(task.getId(), APPLY_OPERATOR);
            });
            System.out.println();
        });

    }

    @Test
    void executeTask() {
        engine.executeTask("1e1aabf977ef48c998408957b8604a62", APPLY_OPERATOR);
    }

    @Test
    void decisionBorrow() {
        Map<String, Object> params = new HashMap<>();
        params.put("result", "agree");
        //params.put("result", "disagree");
        engine.executeTask("1e9c5696734243cea2e9ba5a93478f19", APPROVAL_OPERATOR, params);
    }

    @Test
    void decisionLeave() {
        Map<String, Object> params = new HashMap<>();
        params.put("day", 5);
        engine.executeTask("d888b9afd871491d852f9db24bf12e72", APPROVAL_OPERATOR, params);
    }

    @Test
    void history() {
        List<HistoryOrder> orders = queryService.getHistoryOrders(new QueryFilter().setName("borrow"));
        orders.forEach(order -> {
            System.out.println(order);
            List<HistoryTask> tasks = queryService.getHistoryTasks(new QueryFilter().setOrderId(order.getId()));
            tasks.forEach(task -> {
                System.out.println(task);
            });
            System.out.println();
        });
    }
}
