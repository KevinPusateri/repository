package it.enaip.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import it.enaip.corso.cruddao.DaoUser;
import it.enaip.corso.factory.DataSourceFactory;
import it.enaip.corso.model.JsonConverterUser;
import it.enaip.corso.model.User;
import it.enaip.corso.model.User.Type;
import it.enaip.corso.servlet.UserController;

public class UserTest {

private User user;

	@Before
	public void setup() {
		
		int year = 1999;
		int dd = 04;
		int mm = 02;
		Date date = new Date(year,dd,mm);
		
		user = new User("dfsfds","Apra",date,23,Type.CHILD);
//		user.setName("dfsfds");
//		user.setSurname("Apra");
//		user.setBirthDate(date);
//		user.setAge(23);
//		user.setType(Type.CHILD);
	}
	
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
	
	@Test 
	public void testDelete() throws SQLException, JSONException {
		UserController controller = new UserController();
		JSONObject jobj = controller.getJson("2");
		DaoUser dao = new DaoUser();
		User u = JsonConverterUser.jsonToUser(jobj);
		boolean row = dao.delete(u);
		assertTrue(row);
	}
	
	@Test
	public void testInsert() throws SQLException {
		DaoUser dao = new DaoUser();
		assertNotNull(user);
		assertTrue("Errore, nome vuoto", user.getName().length()>0);
		user.setName("Kevin");
		assertTrue("Contiene numero nel nome", !user.getName().matches(".*\\d.*"));
		
		assertTrue("Errore, cognome vuoto", user.getSurname().length()>0);
		user.setSurname("Pusa");
		assertTrue("Contiene numero nel cognome", !user.getSurname().matches(".*\\d.*"));
		
		user.setAge(23);
		assertTrue("Errore, eta negativo", user.getAge()>0);
//		assertTrue("Errore, insert non eseguito", dao.save(user));
	}
	
	@Test
	public void testFindAll() throws SQLException {
		DaoUser dao = new DaoUser();
		List<User> listUser = dao.findAll();
		assertTrue("record is empty", !listUser.isEmpty());
	}
		


}
