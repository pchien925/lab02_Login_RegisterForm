package ltweb_weekly_proj.pc.services.Impl;

import java.sql.Date;

import ltweb_weekly_proj.pc.dao.IUserDao;
import ltweb_weekly_proj.pc.dao.Impl.UserDaoImpl;
import ltweb_weekly_proj.pc.models.User;
import ltweb_weekly_proj.pc.services.IUserService;

public class UserServiceImpl implements IUserService {

	IUserDao userDAO = new UserDaoImpl();

	@Override
	public void insert(User user) {
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public void updataStatus(User user) {
		userDAO.updataStatus(user);
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(String username) {
		return userDAO.findUserByUsername(username);
	}

	@Override
	public User login(String username, String password) {
		User user = userDAO.findUserByUsername(username);
		if (user != null && password.equals(user.getPassword()))
			return user;
		return null;
	}

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone, int status,
			String code, int roleid, Date createDate) {
		userDAO.insertRegister(new User(username, password, fullname, email, phone, status, code, roleid, createDate));
		return true;
	}

	@Override
	public boolean isActiveAccount(User user) {
		return userDAO.isActiveAccount(user);
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDAO.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDAO.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDAO.checkExistPhone(phone);
	}

}
