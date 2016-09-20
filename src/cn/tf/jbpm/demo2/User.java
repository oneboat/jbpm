package cn.tf.jbpm.demo2;

import java.io.Serializable;

/**
 * 测试 序列化
 * 
 */
public class User implements Serializable {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
