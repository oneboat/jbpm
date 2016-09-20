package cn.tf.jbpm.demo2;

/**
 * Long类型主键的PO对象
 * 
 * 
 */
public class Book {
	private Long id;
	private String bookname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

}
