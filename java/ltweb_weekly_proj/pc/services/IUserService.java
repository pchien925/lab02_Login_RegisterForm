package ltweb_weekly_proj.pc.services;

import java.sql.Date;

import ltweb_weekly_proj.pc.models.User;

public interface IUserService {
	void insert(User user);

	boolean updateProfile(User user);

	void updataStatus(User user);

	User get(int id);

	User get(String username);

	User login(String username, String password);

	boolean register(String email, String password, String username, String fullname, String phone, int status,
			String code, int roleid, Date createDate);

	boolean isActiveAccount(User user);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);
}
