package lab02.pc.services.Impl;

import lab02.pc.dao.IUserDao;
import lab02.pc.dao.Impl.UserDaoImpl;
import lab02.pc.models.User;
import lab02.pc.services.IUserService;

public class UserServiceImpl implements IUserService {

	IUserDao userDAO = new UserDaoImpl();

	@Override
	public void insert(User user) {
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String username, String password) {
		User user = userDAO.findUser(username);
		if (user != null && password.equals(user.getPassword()))
			return user;
		return null;
	}

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone, int status, String code) {
		userDAO.insertRegister(new User(username, password, email, fullname, phone, status, code));
		return true;
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
