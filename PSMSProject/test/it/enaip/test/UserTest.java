package it.enaip.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
		int mm = 02;
		int dd = 04;
		Date date = new Date(year,mm,dd);
		user = new User("Prova","Coc",date,23,Type.CHILD);
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
	public void testDelete() throws SQLException, JSONException, ParseException {
		UserController controller = new UserController();
		DaoUser dao = new DaoUser();
		user = new User("dfsfds","Apra",new SimpleDateFormat("yyyy-MM-dd").parse("1999-12-23"),23,Type.CHILD);
		dao.save(user);
		JSONObject jobj = controller.getJson();
		User u = JsonConverterUser.jsonToUser(jobj);
		boolean row = dao.delete(u);
		assertTrue(row);
	}
	
	@Test
	public void testInsertCorrect() throws SQLException {
		DaoUser dao = new DaoUser();
		assertTrue("Errore, insert non eseguito", dao.save(user));
	}
	
	@Test
	public void testInsertError() throws SQLException {
		DaoUser dao = new DaoUser();
		user.setName(null);
		dao.save(user);
		User u = dao.findUserLast();
		assertTrue("Nome is null", u.getName()==null);
//		assertTrue("Errore, nome vuoto", user.getName().length()>0);
//		user.setName("das");
//		assertTrue("Errore, contiene numero nel nome", !user.getName().matches(".*\\d.*"));
//		
//		user.setName(null);
//		assertNotNull("Nome è null",user.getName());
//
//		assertTrue("Errore, cognome vuoto", user.getSurname().length()>0);
//		user.setSurname("Pusa");
//		assertTrue("Errore, contiene numero nel cognome", !user.getSurname().matches(".*\\d.*"));
//		user.setSurname(null);
//		assertNotNull("Nome è null",user.getSurname());
//
//		user.setAge(23);
//		assertTrue("Errore, eta negativo", user.getAge()>0);
		
		
//		assertTrue("Errore, insert non eseguito", dao.save(user));
	}
	
	@Test
	public void testFindAll() throws SQLException {
		DaoUser dao = new DaoUser();
		List<User> listUser = dao.findAll();
		assertTrue("record is empty", !listUser.isEmpty());
	}
		
}
