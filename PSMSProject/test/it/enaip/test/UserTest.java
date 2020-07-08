package it.enaip.test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import it.enaip.corso.servlet.UserController;

public class UserTest {

	@Test
	public void test() throws JSONException, SQLException {
			UserController controller = new UserController();
			JSONObject jobj = controller.getJson("1");
			JSONAssert.assertEquals("{id:1}",jobj , false);
			JSONAssert.assertEquals("{name:Jason}",jobj , false);
	}

}
