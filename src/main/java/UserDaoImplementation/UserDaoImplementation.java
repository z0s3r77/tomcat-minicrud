package UserDaoImplementation;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;

import UserDao.UserDao;
import model.User;
import database.DatabaseConnection;
import exceptions.UserNameException;

public class UserDaoImplementation implements UserDao {

	static Connection con = DatabaseConnection.getConnection();

	@Override
	public int add(User user) {

		try {

			User checkUserExist = this.getUser(user.getUser());

			try {
				if (checkUserExist != null) {
					throw new UserNameException(String.format("El usuario con nombre %s ya existe", user.getUser()));
				}
			} catch (UserNameException e) {
				e.printStackTrace();
				return 0;
			}

			String query = "insert into users (user_name, user_password, user_rol, user_description) VALUES (?, ?, ? ,?)";

			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, user.getUser());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRol());
			ps.setString(4, user.getDescription());

			int result = ps.executeUpdate();

			if (result > 0) {
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					user.setId(generatedKeys.getInt(1));
				}
			}

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public void delete(String userName) throws SQLException {

		String query = "delete from users where user_name = ?";
		PreparedStatement ps = con.prepareStatement(query);

		ps.setString(1, userName);

		ps.executeUpdate();
	}

	@Override
	public User getUser(String userName) throws SQLException {

		String query = "select * from users where user_name = ?";

		PreparedStatement ps = con.prepareStatement(query);

		ps.setString(1, userName);

		User user = new User();

		ResultSet rs = ps.executeQuery();

		boolean check = false;

		while (rs.next()) {
			check = true;

			user.setId(rs.getInt("user_id"));
			user.setUser(rs.getString("user_name"));
			user.setPassword(rs.getString("user_password"));
			user.setRol(rs.getString("user_rol"));
			user.setDescription(rs.getString("user_description"));
		}

		if (check == true) {
			return user;
		} else {
			return null;
		}
	}

	public User getUser(String username, String password) {

		String query = "select * from users where user_name = ? and user_password = ?";

		try {

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			User user = new User();
			ResultSet rs = ps.executeQuery();

			boolean check = false;

			while (rs.next()) {
				check = true;

				user.setId(rs.getInt("user_id"));
				user.setUser(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
				user.setRol(rs.getString("user_rol"));
				user.setDescription(rs.getString("user_description"));
			}

			if (check == true) {
				return user;
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> getUsers() throws SQLException {

		String query = "select * from users";

		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		List<User> userList = new ArrayList<User>();

		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("user_id"));
			user.setUser(rs.getString("user_name"));
			user.setPassword(rs.getString("user_password"));
			user.setRol(rs.getString("user_rol"));
			user.setDescription(rs.getString("user_description"));
			userList.add(user);
		}
		return userList;

	}

	@Override
	public void update(User user) throws SQLException {

		String query = "update users set user_name=?, user_password=?, user_rol=?, user_description=? where user_id=?";

		PreparedStatement ps = con.prepareStatement(query);

		ps.setString(1, user.getUser());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getRol());
		ps.setString(4, user.getDescription());
		ps.setInt(5, user.getId());


		ps.executeUpdate();
	}

}
