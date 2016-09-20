package cn.tf.jbpm.demo1;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.junit.Test;

//流程实例
public class ProcessInstanceTest {

	//启动流程
	@Test
	public void test1(){
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		ExecutionService  executionService=processEngine.getExecutionService();
		
		executionService.startProcessInstanceById("holiday-1");

	}
	
	//向后流转
	@Test
	// 测试流程实例 向后流转
	public void test2() {
		// 1、获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2、获得对应Service对象
		ExecutionService executionService = processEngine.getExecutionService();
		// 3、根据实例id ，向后流转
		// to 部门经理审批 是transition的name属性
		//executionService.signalExecutionById("holiday.30001", "to t部门经理");

		executionService.signalExecutionById("holiday.30001", "to end1");

	}
	
	//中止流程
	@Test
	// 测试流程实例 向后流转
	public void test3() {
		// 1、获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2、获得对应Service对象
		ExecutionService executionService = processEngine.getExecutionService();
		
		executionService.endProcessInstance("holiday.30001", ProcessInstance.STATE_ENDED);

	}
	
}
