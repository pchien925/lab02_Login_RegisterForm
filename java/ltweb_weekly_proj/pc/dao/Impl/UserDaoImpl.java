package ltweb_weekly_proj.pc.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ltweb_weekly_proj.pc.dao.IUserDao;
import ltweb_weekly_proj.pc.models.User;
import ltweb_weekly_proj.pc.utils.DBConnection;

public class UserDaoImpl implements IUserDao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public void insert(User user) {
		String sqlStr = "insert into users(username, password, images, fullname, email, phone, status, code, roleid, createDate) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sqlStr);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getImages());
			ps.setString(4, user.getFullname());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPhone());
			ps.setInt(7, user.getStatus());
			ps.setString(8, user.getCode());
			ps.setInt(9, user.getRoleid());
			ps.setDate(10, user.getCreateDate());
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updataStatus(User user) {
		String sqlStr = "update users set status = ? where username = ?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sqlStr);

			ps.setInt(1, user.getStatus());
			ps.setString(2, user.getUsername());
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findUserByUsername(String username) {
		String sqlStr = "select * from users where username = ?";
		User user = new User();
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, username);
			rs = ps.executeQuery();

			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setPhone(rs.getString("phone"));
				user.setStatus(rs.getInt("status"));
				user.setCode(rs.getString("code"));
				user.setRoleid(rs.getInt("roleid"));
				user.setCreateDate(rs.getDate("createDate"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	@Override
	public void insertRegister(User user) {
		String sqlStr = "insert into users(username, password, fullname, email, phone, status, code, roleid, createDate) values(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sqlStr);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFullname());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhone());
			ps.setInt(6, user.getStatus());
			ps.setString(7, user.getCode());
			ps.setInt(8, user.getRoleid());
			ps.setDate(9, user.getCreateDate());
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
	public boolean isActiveAccount(User user) {
		if (user.getStatus() == 1)
			return true;
		return false;
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String sqlStr = "select * from users where email = ?";
		try {
			new DBConnection();
			conn = DBConnection.getConnection();
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
			new DBConnection();
			conn = DBConnection.getConnection();
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
			new DBConnection();
			conn = DBConnection.getConnection();
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
