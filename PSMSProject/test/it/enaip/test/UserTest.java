package it.enaip.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	public void setup() throws ParseException{
		String dt = "2008-01-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(dt));
		user = new User("Prova","Coc",c.getTime(),23,Type.CHILD);
	}

	
	@Test
	public void testDbConn() throws SQLException{
		Connection conn = DataSourceFactory.getConnection();
		assertEquals(conn != null,true);
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
		assertTrue(jobj.size() > 0);
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
		user = new User("dfsfds", "Apra", new SimpleDateFormat("yyyy-MM-dd").parse("1999-12-23"), 23, Type.CHILD);
		dao.save(user);
		JSONObject jobj = controller.getJson();
		User u = JsonConverterUser.jsonToUser(jobj);
		assertTrue(dao.delete(u));
	}

	@Test
	public void testFindAll() throws SQLException {
		DaoUser dao = new DaoUser();
		List<User> listUser = dao.findAll();
		assertTrue("record is empty", !listUser.isEmpty());
	}
	
	@Test
	public void testInsertCorrect() throws SQLException {
		DaoUser dao = new DaoUser();
		assertTrue("Error, insert not executed", dao.save(user));
		User u = dao.findUserLast();
		assertTrue(dao.delete(u));
	}
	
	@Test
	public void testNameIsNull() throws SQLException {
		DaoUser dao = new DaoUser();
		user.setName(null);
		assertTrue(dao.save(user));
		User u = dao.findUserLast();
		assertTrue(dao.delete(u));
		assertTrue(u.getName()==null);
	}
	
	@Test
	public void testNameWithNumber() throws SQLException {
		DaoUser dao = new DaoUser();
		user.setName("343432");
		assertTrue(dao.save(user));
		User u = dao.findUserLast();
		assertTrue(dao.delete(u));
		assertTrue( u.getName().matches(".*\\d.*"));
	}
	
	@Test
	public void testSurnameIsNull() throws SQLException {
		DaoUser dao = new DaoUser();
		user.setSurname(null);
		assertTrue(dao.save(user));
		User u = dao.findUserLast();
		assertTrue(dao.delete(u));
		assertTrue(u.getSurname()==null);
	}
	
	@Test
	public void testSurnameWithNumber() throws SQLException {
		DaoUser dao = new DaoUser();
		user.setSurname("Pus34a432t");
		assertTrue(dao.save(user));
		User u = dao.findUserLast();
		assertTrue(dao.delete(u));
		assertTrue( u.getSurname().matches(".*\\d.*"));
	}
	
	@Test
	public void testAgeNegative() throws SQLException {
		DaoUser dao = new DaoUser();
		user.setAge(-23);
		assertTrue(dao.save(user));
		User u = dao.findUserLast();
		assertTrue(dao.delete(u));
		assertTrue(u.getAge()<=0);
	}
	
}
