package it.enaip.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.mockito.Mockito;

import it.enaip.corso.factory.DataSourceFactory;
import it.enaip.corso.model.DaoStuff;
import it.enaip.corso.model.Stuff;

public class StuffDaoTest extends Mockito{
	
	@Test
	public void checkQueryInsert() throws SQLException {
			try (Statement stCheck = DataSourceFactory.getConnection().createStatement()) {
				DataSourceFactory.getConnection().setAutoCommit(false);

				String name = "Kevin";
				String description = "Desc";
				int quantity = 10;
				String location = "l";
				
				Stuff stuff = new Stuff(name, description, quantity, location);
				boolean checkInsert = DaoStuff.getInstance().save(stuff);
				assertEquals(checkInsert, true);
			} finally {
				DataSourceFactory.getConnection().rollback();

			}
		} 
	
	@Test
	public void checkQueryDelete() {
		try (Connection connection = DataSourceFactory.getConnection()) {
			try (Statement stCheck = connection.createStatement()) {
				connection.setAutoCommit(false);
				int id_stuff = 0;
				String name = "Kevin";
				String description = "Desc";
				int quantity = 10;
				String location = "l";
				
				Stuff stuff = new Stuff();		
				stuff.setName(name);
				stuff.setDescription(description);
				stuff.setQuantity(quantity);
				stuff.setLocation(location);
				
				DaoStuff.getInstance().save(stuff);
				String sql = "SELECT Max(stuff_id) FROM stuff";

				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					id_stuff = resultSet.getInt("stuff_id");
					name = resultSet.getString("name");
					description = resultSet.getString("description");
					quantity = resultSet.getInt("quantity");
					location = resultSet.getString("location");
				}
				Stuff s = new Stuff(id_stuff);

				boolean checkDelete = DaoStuff.getInstance().delete(s);
				assertEquals(checkDelete, true);
			} 
		} catch (SQLException e) {
            fail(e.toString());
		}
	}
	
}
