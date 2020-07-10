package it.enaip.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import it.enaip.corso.factory.DataSourceFactory;
import it.enaip.corso.servlet.UserController;

public class UserTest {

	@Test
	public void testRecordIsPresent() throws JSONException, SQLException {
		UserController controller = new UserController();
		JSONObject jobj = controller.getJson("1");
		assertTrue(jobj.length() > 0);
		assertTrue(jobj.has("id"));
		assertTrue(jobj.has("name"));
		assertTrue(jobj.has("surname"));
		assertTrue(jobj.has("birthDate"));
		assertTrue(jobj.has("age"));
		assertTrue(jobj.has("type"));
		assertTrue(jobj.has("sqlTimestamp"));
		
		JSONAssert.assertEquals("{id:1}", jobj, false);
		JSONAssert.assertEquals("{name:Jason}", jobj, false);
	}

	@Test
	public void testTableIsNotEmpty() throws SQLException, JSONException {
		UserController controller = new UserController();
		List<JSONObject> jobj = controller.getJsonArray();
		assertTrue(jobj.size()>0);
	}
	
	@Test
	public void testTableExists() throws SQLException {
		String tableName = "users";
		Connection conn = DataSourceFactory.getConnection();
		boolean exists = conn.getMetaData().getTables(null, null, tableName.toUpperCase(), null).next();
		assertTrue(exists);
	}
}
