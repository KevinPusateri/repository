package it.enaip.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.junit.Test;

import it.enaip.corso.factory.DataSourceFactory;
import it.enaip.corso.model.DaoStuff;
import it.enaip.corso.model.Stuff;

public class StuffDaoTest {
	
	@Test
	public void checkQueryInsert() {
		try (Connection connection = DataSourceFactory.getConnection()) {
			try (Statement stCheck = connection.createStatement()) {
				connection.setAutoCommit(false);

				String name = "name";
				String description = "description";
				int quantity = 10;
				String location = "test";
				
				Stuff stuff = new Stuff(name, description, quantity, location);
				boolean checkInsert = DaoStuff.getInstance().save(stuff);
				assertEquals(checkInsert, true);
			} finally {
				connection.rollback();
			}
		} catch (SQLException e) {
            fail(e.toString());
		}
	}

	
	@Test
	public void checkQueryDelete() {
		try (Connection connection = DataSourceFactory.getConnection()) {
			try (Statement stCheck = connection.createStatement()) {
				connection.setAutoCommit(false);
				int id_stuff = 0;
				String name = "name";
				String description = "description";
				int quantity = 10;
				String location = "test";
				
				Stuff stuff = new Stuff();		
				stuff.setName(name);
				stuff.setDescription(description);
				stuff.setQuantity(quantity);
				stuff.setLocation(location);
				
				DaoStuff.getInstance().save(stuff);
				String sql = "SELECT stuff_id,name,description,quantity,location FROM stuff WHERE stuff_id=?";
				Connection conn = DataSourceFactory.getConnection();

				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, String.valueOf(stuff.getId()));
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					id_stuff = resultSet.getInt("stuff_id");
					name = resultSet.getString("name");
					description = resultSet.getString("description");
					quantity = resultSet.getInt("quantity");
					location = resultSet.getString("location");
				}
				Stuff s = new Stuff(id_stuff, name, description, quantity, location);

				boolean checkDelete = DaoStuff.getInstance().delete(s);
				assertEquals(checkDelete, true);
			} 
		} catch (SQLException e) {
            fail(e.toString());
		}
	}
}
