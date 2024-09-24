package ltweb_weekly_proj.pc.models;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private String password;
	private String images;
	private String fullname;
	private String email;
	private String phone;
	private int status;
	private String code;

	private int roleid;
	private Date createDate;

	public User() {
		super();
	}

	public User(String username, String images, String fullname, String phone) {
		super();
		this.username = username;
		this.images = images;
		this.fullname = fullname;
		this.phone = phone;
	}

	public User(String username, String password, String images, String fullname, String email, String phone,
			int status, String code, int roleid, Date createDate) {
		super();
		this.username = username;
		this.password = password;
		this.images = images;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.code = code;
		this.roleid = roleid;
		this.createDate = createDate;
	}

	public User(int id, String username, String password, String images, String fullname, String email, String phone,
			int status, String code, int roleid, Date createDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.images = images;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.code = code;
		this.roleid = roleid;
		this.createDate = createDate;
	}

	public User(String username, String password, String fullname, String email, String phone, int status, String code,
			int roleid, Date createDate) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.code = code;
		this.roleid = roleid;
		this.createDate = createDate;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", images=" + images
				+ ", fullname=" + fullname + ", email=" + email + ", phone=" + phone + ", status=" + status + ", code="
				+ code + ", roleid=" + roleid + ", createDate=" + createDate + "]";
	}

}
