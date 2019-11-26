package serverCloudApi.entitys;

import java.io.Serializable;
public class User implements Serializable {
	private int id;
	private String name;
	private String pass;
	private String dbSource;
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDbSource() {
		return dbSource;
	}
	public void setDbSource(String dbSource) {
		this.dbSource = dbSource;
	}
	public User(int id, String name, String pass, String dbSource) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.dbSource = dbSource;
	}
	public User() {
		super();
	}
	
	
	
}
