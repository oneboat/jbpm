package cn.tf.jbpm.handler;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

public class MyPersonlTaskHandler implements AssignmentHandler {
	@Override
	// Assignable对象 用来指定任务负责人
	// OpenExecution 用来读写流程变量
	public void assign(Assignable assignable, OpenExecution openExecution) throws Exception {
		// 指定任务负责人
		assignable.setAssignee("老毕");
	}
}
