package cn.tf.jbpm.demo3;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.junit.Test;

//流程根节点
public class ProcessTest {
	//发布流程定义
	@Test
	public  void test1(){
		//获得流程引擎
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		RepositoryService repositoryService=processEngine.getRepositoryService();
		repositoryService.createDeployment().addResourceFromClasspath(("process.jpdl.xml")).deploy();
		
	}
	
	

}
