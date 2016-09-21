package cn.tf.jbpm.handler;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

public class MyGroupTaskHandler implements AssignmentHandler {
	@Override
	public void assign(Assignable assignable, OpenExecution openExecution) throws Exception {
		// 向系统指定组用户
		assignable.addCandidateUser("小丽"); // candidate-users
		assignable.addCandidateUser("小明");

		assignable.addCandidateGroup("财务");// candidate-groups
	}
}
