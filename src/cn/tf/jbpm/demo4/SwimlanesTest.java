package cn.tf.jbpm.demo4;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;

/**
 * 测试泳道的使用
 * 
 * 
 */
public class SwimlanesTest {
	@Test
	// 发布流程定义，启动实例
	public void demo1() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getRepositoryService().createDeployment().addResourceFromClasspath("swimlanes.jpdl.xml").deploy();
		processEngine.getExecutionService().startProcessInstanceByKey("swimlanes");
	}

	@Test
	// 拾取操作
	public void demo2() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().takeTask("8", "李四");
	}

	@Test
	// 办理申请
	public void demo3() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("8");
	}

	@Test
	// 办理发钱
	public void demo4() {
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		processEngine.getTaskService().completeTask("10001");
	}
}
