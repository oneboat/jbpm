package cn.tf.jbpm.demo1;

import org.jbpm.api.Configuration;
import org.junit.Test;

public class JBPMInitTest {
	
	@Test
	public void test1(){
		Configuration configuration=new Configuration();
		configuration.buildProcessEngine();
		
	}
	

}
