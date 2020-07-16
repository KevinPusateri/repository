package it.enaip.corso.cruddao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.enaip.corso.factory.DataSourceFactory;
import it.enaip.corso.model.User;
import it.enaip.corso.model.User.Type;

public class DaoUser implements UserDao {

	private static class SingletonHelper {
		private static final DaoUser INSTANCE = new DaoUser();
	}

	public static DaoUser getInstance() {
		return SingletonHelper.INSTANCE;
	}

	@Override
	public Optional<User> find(String id) throws SQLException {
		String sql = "SELECT * FROM users WHERE id=?";
		int id_user = 0, age = 0;
		String name = "", surname = "", type = "";
		Date birthDate = null;
		Connection conn = DataSourceFactory.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			id_user = resultSet.getInt("id");
			name = resultSet.getString("name");
			surname = resultSet.getString("surname");
			birthDate = resultSet.getDate("birthDate");
			age = resultSet.getInt("age");
			type = resultSet.getString("type");
			type = String.valueOf(User.getEnum(type));
		}
		
		conn.close();

		return Optional.of(new User(id_user, name, surname, birthDate, age, Type.valueOf(type)));
	}

	@Override
	public List<User> findAll() throws SQLException {
		List<User> listUser = new ArrayList<User>();
		String sql = "SELECT id,name,surname,birthDate,age,type FROM users";

		Connection conn = DataSourceFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String surname = resultSet.getString("surname");
			java.sql.Date birthDate = resultSet.getDate("birthDate");
			int age = resultSet.getInt("age");
			String type = resultSet.getString("type");
			type = String.valueOf(User.getEnum(type));
			User user = new User(id, name, surname, birthDate, age, Type.valueOf(type));

			listUser.add(user);
		}
		conn.close();

		return listUser;
	}

	@Override
	public boolean save(User user) throws SQLException {
		
		String sql = "INSERT INTO users (name, surname, birthDate, age, type, time) VALUES(?,?,?,?,?,?)";
		boolean rowInserted = false;
		Connection conn = DataSourceFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setString(2, user.getSurname());
		statement.setDate(3, new java.sql.Date(user.getBirthDate().getTime()));
		statement.setInt(4, user.getAge());
		statement.setString(5, user.getValueType());
		statement.setTimestamp(6, user.getSqlTimestamp());
		rowInserted = statement.executeUpdate() > 0;
		conn.close();

		return rowInserted;
	}

	@Override
	public boolean update(User user) throws SQLException {
		String sql = "UPDATE users SET name=?, surname=?, birthDate=?, age=?, type=?, time=?";
		sql += "WHERE id=?";

		boolean rowUpdated = false;
		Connection conn = DataSourceFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setString(2, user.getSurname());
		statement.setDate(3, new java.sql.Date(user.getBirthDate().getTime()));
		statement.setInt(4, user.getAge());
		statement.setString(5, user.getValueType());
		statement.setTimestamp(6, user.getSqlTimestamp());
		statement.setInt(7, user.getId());
		rowUpdated = statement.executeUpdate() > 0;
		conn.close();

		return rowUpdated;
	}

	@Override
	public boolean delete(User user) throws SQLException {
		String sql = "DELETE FROM users WHERE id = ?";
		boolean rowDeleted = false;
		Connection conn = DataSourceFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, user.getId());
		rowDeleted = statement.executeUpdate() > 0;
		conn.close();

		return rowDeleted;
	}

	
	public User findUser(String id) throws SQLException {
		String sql = "SELECT * FROM users WHERE id=?";
		int id_user = 0, age = 0;
		String name = "", surname = "", type = "";
		Date birthDate = null;
		Connection conn = DataSourceFactory.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			id_user = resultSet.getInt("id");
			name = resultSet.getString("name");
			surname = resultSet.getString("surname");
			birthDate = resultSet.getDate("birthDate");
			age = resultSet.getInt("age");
			type = resultSet.getString("type");
			type = String.valueOf(User.getEnum(type));

		}
		conn.close();
		return  new User(id_user, name, surname, birthDate, age, Type.valueOf(type));
	}
	
	public User findUserLast() throws SQLException {
		String sql = "SELECT * " + 
				"FROM users " + 
				"WHERE id=( " + 
				"    SELECT Max(id) FROM users " + 
				"    )";
		int id_user = 0, age = 0;
		String name = "", surname = "", type = "";
		Date birthDate = null;
		Connection conn = DataSourceFactory.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			id_user = resultSet.getInt("id");
			name = resultSet.getString("name");
			surname = resultSet.getString("surname");
			birthDate = resultSet.getDate("birthDate");
			age = resultSet.getInt("age");
			type = resultSet.getString("type");
			type = String.valueOf(User.getEnum(type));
		}
		conn.close();

		return  new User(id_user, name, surname, birthDate, age, Type.valueOf(type));
	}
}
