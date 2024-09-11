package lab02.pc.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lab02.pc.dao.IUserDao;
import lab02.pc.models.User;
import lab02.pc.utils.DBConnection;

public class UserDaoImpl implements IUserDao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUser(String username) {
		String sqlStr = "select * from users where username = ?";
		User user = new User();
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, username);
			rs = ps.executeQuery();

			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setPhone(rs.getString("phone"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	@Override
	public void insertRegister(User user) {
		String sqlStr = "insert into users(username, password, email, fullname, phone) values(?, ?, ?, ?, ?)";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sqlStr);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getPhone());
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String sqlStr = "select * from users where email = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}

			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String sqlStr = "select * from users where username = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}

			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String sqlStr = "select * from users where phone = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}

			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duplicate;
	}
}
