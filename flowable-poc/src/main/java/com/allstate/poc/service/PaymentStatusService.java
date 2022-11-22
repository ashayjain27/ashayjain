package com.allstate.poc.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Transactional service class
 * @author A s H a Y
 */
@Service
public class PaymentStatusService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    public static Map<String, Object> getTestValues() {
        final Map<String, Object> allVals = new HashMap<>();

        allVals.put("id", "1");
        allVals.put("user", "abc");
        allVals.put("event", "java");
        allVals.put("payment", Boolean.TRUE);
        return allVals;
    }

    @Transactional
    public void startProcess() {
        final Map eventValues = PaymentStatusService.getTestValues();
        runtimeService.startProcessInstanceByKey("paymentreview", eventValues);
    }

    @Transactional
    public List<Task> getTasks(String assignee) {
        List<Task> allTasks = taskService.createTaskQuery().taskCandidateGroup("eventmanager").list();
        return allTasks;
    }

    @Transactional
    public void submitReview(Map<String, Object> approvalStatus, Task task) {
        taskService.complete(task.getId(),approvalStatus);
    }
}
