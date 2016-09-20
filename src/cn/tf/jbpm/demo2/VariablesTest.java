package cn.tf.jbpm.demo2;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.TaskService;
import org.junit.Test;

/**
 * 测试 流程变量 读写
 * 
 */
public class VariablesTest {
	@Test
	// 启动流程时，关联变量
	public void demo1() {
		// 1、 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2、 获得对应Service
		ExecutionService executionService = processEngine.getExecutionService();

		// 3、启动流程实例
		// 关联流程变量
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("company", "指令汇科技");
		executionService.startProcessInstanceByKey("holiday", variables);
	}

	@Test
	// 使用ExecutionService 读写流程变量
	public void demo2() {
		// 1、 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2、 获得对应Service
		ExecutionService executionService = processEngine.getExecutionService();

		// 读写流程变量
		String company = (String) executionService.getVariable("holiday.30001", "company");
		System.out.println(company);

		executionService.setVariable("holiday.30001", "pnum", 100);
	}

	@Test
	// 使用TaskService 读写流程变量
	public void demo3() {
		// 1、 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2、获得Service
		TaskService taskService = processEngine.getTaskService();

		// 读取变量
		int pnum = (Integer) taskService.getVariable("30003", "pnum");
		System.out.println(pnum);

		// 写入变量
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("weather", "多云无雨");
		taskService.setVariables("30003", variables);
	}

	@Test
	// 测试保存 可序列化的对象
	public void demo4() {
		// 1、 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2、 获得对应Service
		ExecutionService executionService = processEngine.getExecutionService();

		// 保存user对象
		User user = new User();
		user.setId(1);
		user.setName("张三");
		executionService.setVariable("holiday.80001", "user", user);
	}

	@Test
	// 测试保存 持久化 PO对象
	public void demo5() {
		// 1、 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 2、 获得对应Service
		ExecutionService executionService = processEngine.getExecutionService();

		Session session = new org.hibernate.cfg.Configuration().configure("jbpm.hibernate.cfg.xml").buildSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Book book = new Book();
		book.setBookname("jbpm入门");
		session.save(book);

		transaction.commit();
		session.close();

		executionService.setVariable("holiday.30001", "book", book);
	}

}
