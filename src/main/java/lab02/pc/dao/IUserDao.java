package lab02.pc.dao;

import lab02.pc.models.User;

public interface IUserDao {
	void insert(User user);
	void update(User user);
	User findUser(String username);
	void insertRegister(User user);
	
	User get(int id);
	User get(String username);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String usernames);
	boolean checkExistPhone(String phone);
}
	