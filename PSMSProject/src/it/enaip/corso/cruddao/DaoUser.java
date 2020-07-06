package it.enaip.corso.cruddao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		String sql = "SELECT * FROM stuff WHERE id=?";
		int id_user = 0, age = 0;
		String name = "", surname = "", birthDate = "",type = "";
		Connection conn = DataSourceFactory.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			id_user = resultSet.getInt("id");
			name = resultSet.getString("name");
			surname = resultSet.getString("surname");
			birthDate = resultSet.getString("birthDate");
			age = resultSet.getInt("age");
			type = resultSet.getString("type");
		}
		return Optional.of(new User(id_user, name, surname, birthDate, age, Type.valueOf(type)));
	}

	@Override
	public List<User> findAll() throws SQLException {
		List<User> listUser = new ArrayList<User>();
		String sql = "SELECT * FROM users";

		Connection conn = DataSourceFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String surname = resultSet.getString("surname");
			String birthDate = resultSet.getString("birthDate");
			int age = resultSet.getInt("quantity");
			String type = resultSet.getString("type");

			User user = new User(id, name, surname, birthDate, age, Type.valueOf(type));
			listUser.add(user);
		}
		return listUser;
	}

	@Override
	public boolean save(User user) throws SQLException {
		String sql = "INSERT INTO user (name, surname, birthDate, age, type, time) VALUES(?,?,?,?,?,?)";
		boolean rowInserted = false;
		Connection conn = DataSourceFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setString(2, user.getSurname());
		statement.setString(3, user.getBirthDate());
		statement.setInt(4, user.getAge());
		statement.setString(5, user.getType().getDescType());
		String time = "";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
		time = dateFormat.format(new Date());
		statement.setString(6, time);
		rowInserted = statement.executeUpdate() > 0;

		return rowInserted;
	}

	@Override
	public boolean update(User user) throws SQLException {
		String sql = "UPDATE users SET name=?, surname=?, birthDate=?, age=?, type=?, time=?";
		sql += "WHERE stuff_id=?";

		boolean rowUpdated = false;
		Connection conn = DataSourceFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setString(2, user.getSurname());
		statement.setString(3, user.getBirthDate());
		statement.setInt(4, user.getAge());
		statement.setString(5, user.getType().getDescType());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
		String time = "";
		time = dateFormat.format(new Date());
		statement.setString(6, time);
		statement.setInt(7, user.getId());
		rowUpdated = statement.executeUpdate() > 0;

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
		
		return rowDeleted;
	}

}
