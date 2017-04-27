package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import data_classes.User;

public class UserDAO {
	Connection connection = null;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (connection == null)
				connection = DriverManager.getConnection("jdbc:mysql://localhost/archive", "root", "root");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return connection;
	}

    public void insert(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO archive.users (user_id, login, password, admin) VALUES (NULL, ? , ? , ? )");
            preparedStatement.setString(1,  user.getUsername());
            preparedStatement.setString(2,  user.getPassword());
            preparedStatement.setBoolean(3,  user.isAdmin());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }

	public List<User> select() {
		List<User> userList = new LinkedList<User>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM archive.users");

			User user = null;
			while (resultSet.next()) {
				user = new User();
				user.setId(Integer.parseInt(resultSet.getString("user_id")));
				user.setAdmin(Boolean.parseBoolean(resultSet.getString("admin")));
				user.setUsername(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));

				userList.add(user);
			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public User selectUser(String login) {
		User user = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM archive.users where login = '"+login+"'");
			while (resultSet.next()) {
				user = new User();
				user.setId(Integer.parseInt(resultSet.getString("user_id")));
				user.setAdmin(Boolean.parseBoolean(resultSet.getString("admin")));
				user.setUsername(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			// do nothing
		}
	}
}
