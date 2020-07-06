package it.enaip.corso.cruddao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import it.enaip.corso.factory.DataSourceFactory;
import it.enaip.corso.model.User;

public class DaoUser implements UserDao {

	
	private static class SingletonHelper {
		private static final DaoUser INSTANCE = new DaoUser();
	}

	public static DaoUser getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	@Override
	public Optional<User> find(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() throws SQLException {
		return null;

	}

	@Override
	public boolean save(User o) throws SQLException {
		String sql = "INSERT INTO user (name, description, quantity, location) VALUES(?,?,?,?)";
		boolean rowInserted = false;
		Connection conn = DataSourceFactory.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setString(1, user.getName());
		rowInserted = statement.executeUpdate() > 0;

		return rowInserted;
	}

	@Override
	public boolean update(User o) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User o) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
