package cn.tf.jbpm.demo3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.task.Task;
import org.junit.Test;


//测试判断节点
public class PersonalTaskTest {

	@Test
	// 发布流程定义，启动实例
	public void demo1() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getRepositoryService().createDeployment().addResourceFromClasspath("personaltask.jpdl.xml").deploy();
		processEngine.getExecutionService().startProcessInstanceByKey("personaltask");
	}

	
	//查看个人任务
	@Test
	public void test2(){
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		List<Task>  list=processEngine.getTaskService().findPersonalTasks("张三");
		System.out.println(list.size());
		for (Task task : list) {
			System.out.println(task.getId());
			System.out.println(task.getName());
		}
	}

	
	//办理员工请假任务
	@Test
	public void test3(){
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		Map<String,Object> variable=new HashMap<String,Object>();
		variable.put("manager", "老王");
		processEngine.getTaskService().setVariables("230008", variable);
		processEngine.getTaskService().completeTask("230008");
		
	}
	
	//部门经理审批
	@Test
	public void test5(){
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("240002");
	}
	
	@Test
	// 更换总经理审批负责人
	public void test6() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().assignTask("240002", "老黎");
	}
	
	
}
