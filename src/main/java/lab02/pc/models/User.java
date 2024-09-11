package lab02.pc.models;

import java.io.Serializable;

public class User implements Serializable {
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
	private int status;
	private String code;

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

	public User(String username, String password, String email, String fullname, String phone, int status,
			String code) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.phone = phone;
		this.status = status;
		this.code = code;
	}

	public User(String username, String password, String email, int status, String code) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.status = status;
		this.code = code;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "userModel [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}

}
