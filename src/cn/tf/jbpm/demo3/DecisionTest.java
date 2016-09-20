package cn.tf.jbpm.demo3;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;


//测试判断节点
public class DecisionTest {

	@Test
	// 发布流程定义，启动实例
	public void demo1() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getRepositoryService().createDeployment().addResourceFromClasspath("decision1.jpdl.xml").deploy();
		processEngine.getExecutionService().startProcessInstanceByKey("decision1");
	}

	@Test
	// 购买火车票
	public void demo2() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 设置流程变量
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("condition", "to 领导免费");
		processEngine.getTaskService().setVariables("170008", variables);

		processEngine.getTaskService().completeTask("170008");
	}
	
	@Test
	// 发布流程定义，启动实例
	public void demo3() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getRepositoryService().createDeployment().addResourceFromClasspath("decision2.jpdl.xml").deploy();
		processEngine.getExecutionService().startProcessInstanceByKey("decision2");
	}

	@Test
	// 购买火车票
	public void demo4() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("190008");
	}
	
}
