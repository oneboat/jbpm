package cn.tf.jbpm.demo1;

import java.util.List;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.junit.Test;

public class TaskTest {
	
	
	@Test
	public void test1(){
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		
		TaskService  taskService=processEngine.getTaskService();
		TaskQuery  taskQuery=taskService.createTaskQuery();
		List<Task>  list=taskQuery.executionId("holiday.60001").list();
		
		for (Task task : list) {
			System.out.println(task.getId());
			System.out.println(task.getName());
		}
	}

	
	//查看个人任务
	@Test
	public void test2(){
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		
		TaskService  taskService=processEngine.getTaskService();
		List<Task>  list=taskService.findPersonalTasks("经理");
		for (Task task : list) {
			System.out.println(task.getId());
			System.out.println(task.getName());
		}
	}
	
	
	//办理任务
	@Test
	public void test3(){
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		
		TaskService  taskService=processEngine.getTaskService();
		taskService.completeTask("60002");
	}
	
	
}
