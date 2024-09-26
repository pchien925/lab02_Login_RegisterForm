package ltweb_weekly_proj.pc.dao;

import ltweb_weekly_proj.pc.models.User;

public interface IUserDao {
	void insert(User user);

	boolean updateProfile(User user);

	void updataStatus(User user);

	User findUserByUsername(String username);

	void insertRegister(User user);

	User get(int id);

	User get(String username);

	boolean isActiveAccount(User user);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String usernames);

	boolean checkExistPhone(String phone);
}
