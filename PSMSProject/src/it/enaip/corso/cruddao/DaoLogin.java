package it.enaip.corso.cruddao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.enaip.corso.factory.DataSourceFactory;
import it.enaip.corso.model.Login;

public class DaoLogin implements LoginDao {


	private static class SingletonHelper {
		private static final DaoLogin INSTANCE = new DaoLogin();
	}

	public static DaoLogin   getInstance() {
		return SingletonHelper.INSTANCE;
	}
	
	
	@Override
	public Optional<Login> find(String id) throws SQLException {
		String sql= "SELECT * FROM login WHERE id=?";
		int id_user=0, user_id=0;
		String username="", password="";
		Connection conn= DataSourceFactory.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			id_user=resultSet.getInt("id_user");
			user_id=resultSet.getInt("id");
			username=resultSet.getString("username");
			password=resultSet.getString("password");
		}
		return Optional.of(new Login(username, password, id_user, user_id));
	}

	@Override
	public List<Login> findAll() throws SQLException {
		List<Login> login=new ArrayList<>();
		String sql="SELECT id,id_user,username,password FROM login";
		Connection conn= DataSourceFactory.getConnection();
		PreparedStatement statement= conn.prepareStatement(sql);
		ResultSet resultSet= statement.executeQuery(sql);
		while(resultSet.next()) {
			int id= resultSet.getInt("id");
			int id_user=resultSet.getInt("id_user");
		    String	username=resultSet.getString("username");
		    String password= resultSet.getString("password");
			Login log= new Login(username,password,id,id_user);
			login.add(log);
		}
		return login;
	}

	@Override
	public boolean save(Login o) throws SQLException {
		return false;
	}

	@Override
	public boolean update(Login o) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Login o) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
     
    public Login findUser(String id) throws SQLException {
    	String sql="SELCET name, surname FROM users AS t1 RIGTH JOIN login AS t2"
    			+ " IN t1.id=t2.id"
    			+ " WHERE t2.username=?";
    	int user_id=0,id_user=0;
    	String username="",password="";
    	Connection conn= DataSourceFactory.getConnection();
    	
    	PreparedStatement statement=conn.prepareStatement(sql);
    	statement.setString(1, id);
    	ResultSet resultSet=statement.executeQuery();
    	if(resultSet.next()) {
    		user_id=resultSet.getInt("id");
    		id_user=resultSet.getInt("id_user");
    		username=resultSet.getString("username");
    		password=resultSet.getString("password");
    	}
    	return  new Login(username,password,user_id,id_user);
    }
	
	
	
}
