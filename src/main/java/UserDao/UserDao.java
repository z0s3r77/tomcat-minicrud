package UserDao;

import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserDao {

	public int add(User user) throws SQLException;
	public void delete(String userName) throws SQLException;
	public User getUser(String  userName) throws SQLException;
	public List<User> getUsers() throws SQLException;
	public void update(User user) throws SQLException;
	
}
