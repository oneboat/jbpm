package cn.tf.jbpm.demo1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.Configuration;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.junit.Test;

public class ProcessDefinitionTest {
	
	//发布流程定义
	@Test
	public void test1(){
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		
		//获得对应的service
		RepositoryService  repositoryService=processEngine.getRepositoryService();
		NewDeployment  deployment=repositoryService.createDeployment();
		deployment.addResourceFromClasspath("holiday.jpdl.xml");
		deployment.addResourceFromClasspath("holiday.png");
		deployment.deploy();
				
	}
	
	@Test
	public void test2() throws FileNotFoundException{
		
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		
		//获得对应的service
		RepositoryService  repositoryService=processEngine.getRepositoryService();
		NewDeployment  deployment=repositoryService.createDeployment();
		deployment.addResourcesFromZipInputStream(new ZipInputStream(new FileInputStream("holiday.zip")));
		
		deployment.deploy();		
	}
	
	
	//查看定义
	@Test
	// 流程定义查看
	public void test3() {
		// 步骤一： 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 步骤二： 获得对应Service
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 步骤三 ：业务操作
		// ProcessDefinitionQuery 用来查询 jbpm4_deployprop 表
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		processDefinitionQuery.processDefinitionId("holiday-1");
		List<ProcessDefinition> list = processDefinitionQuery.list();
		System.out.println(list.size());
		for (ProcessDefinition processDefinition : list) {
			System.out.println("ID:" + processDefinition.getId());
			System.out.println("名称：" + processDefinition.getName());
			System.out.println("Key：" + processDefinition.getKey());
			System.out.println("版本：" + processDefinition.getVersion());
		}
	}

	
	//查看流程图
	@Test
	// 查询 holiday-2 流程定义 对应流程图
	public void test4() throws IOException {
		// 步骤一： 获得流程引擎
		ProcessEngine processEngine = new Configuration().buildProcessEngine();
		// 步骤二： 获得对应Service
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 步骤三： 相关操作
		// 先根据 流程定编号 pdId ，查询流程发布编号 deploymentId
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		processDefinitionQuery.processDefinitionId("holiday-2");
		ProcessDefinition processDefinition = processDefinitionQuery.uniqueResult();
		String deploymentId = processDefinition.getDeploymentId(); // 发布id

		// 获得资源名称
		String resourceName = processDefinition.getImageResourceName(); // 资源name

		// 获得图片资源流
		InputStream in = repositoryService.getResourceAsStream(deploymentId, resourceName);
		OutputStream out = new FileOutputStream("d:/holiday.png");
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		in.close();
		out.close();
	}

	//删除流程
	@Test
	public void test5(){
		ProcessEngine  processEngine=new Configuration().buildProcessEngine();
		RepositoryService  repositoryService=processEngine.getRepositoryService();
		repositoryService.deleteDeploymentCascade("10001");
		
	}
	
	
	
}
