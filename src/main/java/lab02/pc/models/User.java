package lab02.pc.models;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String fullname;
	private String phone;
	
	public User() {
		super();
	}
	
	public User(String username, String password, String email, String fullname, String phone) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.phone = phone;
	}

	public User(int id, String username, String password, String email, String fullname, String phone) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.phone = phone;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "userModel [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}
	
}
