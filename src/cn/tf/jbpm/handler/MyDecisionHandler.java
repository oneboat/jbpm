package cn.tf.jbpm.handler;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

/**
 * 判断 handler
 * 
 * 
 */
public class MyDecisionHandler implements DecisionHandler {
	@Override
	// 参数 openExecution，作用是用来读写流程变量
	public String decide(OpenExecution openExecution) {
		return "to 学生半票";
	}
}
