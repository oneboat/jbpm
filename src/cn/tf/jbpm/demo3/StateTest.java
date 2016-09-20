package cn.tf.jbpm.demo3;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.junit.Test;

public class StateTest {
	
	@Test
	public void test1(){
		//获得流程引擎
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		RepositoryService repositoryService=processEngine.getRepositoryService();
		repositoryService.createDeployment().addResourceFromClasspath(("state.jpdl.xml")).deploy();
		processEngine.getExecutionService().startProcessInstanceByKey("state");
	}
	
	
	@Test
	public void test2(){
		//获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("150008");
	}
	
	@Test
	// 收到支付宝回应
	public void test3() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getExecutionService().signalExecutionById("state.150007");
	}

}
