package cn.tf.jbpm.demo4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.task.Participation;
import org.jbpm.api.task.Task;
import org.junit.Test;


//测试判断节点
public class GroupTest {

	@Test
	// 发布流程定义，启动实例
	public void test1() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getRepositoryService().createDeployment().addResourceFromClasspath("grouptask.jpdl.xml").deploy();
		processEngine.getExecutionService().startProcessInstanceByKey("grouptask");
	}

	//组任务的操作
	@Test
	public void test2(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		List<Task>  list=processEngine.getTaskService().findGroupTasks("张三");
		for (Task task : list) {
			System.out.println(task.getId());
			System.out.println(task.getName());
		}
	}
	
	
	//拾取组任务
	@Test
	public void test3(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().takeTask("260008","王五");
	}
	
	//更换主任务负责人
	@Test
	public void test4(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().assignTask("260008", null);
	}
	
	//办理填写财务报销申请任务
	@Test
	public void test5(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("260008");
	}
	
	//添加用户和组
	@Test
	public void test6(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		IdentityService  identityService=processEngine.getIdentityService();
			
		//创建组
		identityService.createGroup("经理");
		identityService.createUser("1", "张", "张三");
		identityService.createUser("2", "李", "李四");
		identityService.createUser("3", "王", "王大锤");
		
		identityService.createMembership("1", "经理");
		identityService.createMembership("2", "经理");
		identityService.createMembership("3", "经理");
	}
	
	//查询经理审批
	@Test
	public void test7(){
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		List<Task>  list=processEngine.getTaskService().findGroupTasks("2");
		for (Task task : list) {
			System.out.println(task.getId());
			System.out.println(task.getName());
		}
			
	}
	
	@Test
	// 办理 经理审批
	public void test8() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("270001");
	}

	@Test
	// 公司临时决定 有 小红，参与财务拨款的组任务
	public void test9() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().addTaskParticipatingUser("300001", "小红", Participation.CANDIDATE);
	}
	
}
