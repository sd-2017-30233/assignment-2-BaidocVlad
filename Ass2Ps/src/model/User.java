package model;

public class User {
	private int id;
	private String name;
	private String userName;
	private String password;
	private String role;
	
	public User(int id, String name, String userName, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public int getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setId(int id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id="+id+"name=" + name + ", userName=" + userName + ", password="
				+ password + ", role=" + role + "]";
	}
}
