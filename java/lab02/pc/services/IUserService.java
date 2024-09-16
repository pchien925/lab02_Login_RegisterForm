package lab02.pc.services;

import java.sql.Date;

import lab02.pc.models.User;

public interface IUserService {
	void insert(User user);

	void update(User user);

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
