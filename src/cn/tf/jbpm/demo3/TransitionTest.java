package cn.tf.jbpm.demo3;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.junit.Test;

public class TransitionTest {
	
	@Test
	public  void test1(){
		//获得流程引擎
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		RepositoryService repositoryService=processEngine.getRepositoryService();
		repositoryService.createDeployment().addResourceFromClasspath(("transition.jpdl.xml")).deploy();
		
	}
	
	
	@Test
	public  void test2(){
		//获得流程引擎
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		
		ExecutionService  executionService=processEngine.getExecutionService();
		executionService.startProcessInstanceByKey("transition");
		
	}
	
	//向后一步
	@Test
	public  void test3(){
		//获得流程引擎
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		
		ExecutionService  executionService=processEngine.getExecutionService();
		executionService.signalExecutionById("transition.120001");
		
	}
	

}
