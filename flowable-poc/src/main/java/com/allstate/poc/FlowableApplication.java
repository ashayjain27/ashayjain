package com.allstate.poc;

import com.allstate.poc.service.PaymentStatusService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class FlowableApplication implements CommandLineRunner {

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private PaymentStatusService paymentStatusService;

	@Autowired
	HistoryService historyService;

	@Autowired
	private TaskService taskService;

	public static void main(String[] args) {
		SpringApplication.run(FlowableApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Number of process definitions : "
				+ repositoryService.createProcessDefinitionQuery().count());
		System.out.println("Number of tasks : " + taskService.createTaskQuery().count());

		paymentStatusService.startProcess();
		System.out.println("========================================================");
		final List<Task> allTasks = paymentStatusService.getTasks("eventmanager");
		System.out.println("All Tasks ::" + allTasks.size());
		System.out.println("========================================================");

		System.out.println("=========================================================");
		Map allDecision = new HashMap();
		allDecision.put("approved", Boolean.TRUE);
		paymentStatusService.submitReview(allDecision, allTasks.get(0));
		System.out.println("All Task Complete:: " + historyService.createHistoricActivityInstanceQuery()
				.processInstanceId("paymentreview").finished().orderByHistoricActivityInstanceEndTime().asc().list());
		System.out.println("=========================================================");





	}
}
