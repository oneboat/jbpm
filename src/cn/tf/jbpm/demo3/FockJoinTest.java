package cn.tf.jbpm.demo3;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;


//测试判断节点
public class FockJoinTest {

	@Test
	// 发布流程定义，启动实例
	public void demo1() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getRepositoryService().createDeployment().addResourceFromClasspath("forkjoin.jpdl.xml").deploy();
		processEngine.getExecutionService().startProcessInstanceByKey("forkjoin");
	}

	@Test
	public void tese2(){
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("210008");
	}
	
	@Test
	public void tese3(){
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("220003");
	}
	
	
}
