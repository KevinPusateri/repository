package it.enaip.test;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class UserTest {


	@Test
	public void test() throws JSONException {
		JSONObject actual = new JSONObject();
		actual.put("id", 1);
		actual.put("name", "Jason");

		JSONAssert.assertEquals("{id:1}", actual, false); 					//Pass, extended fields doesn't matter
		JSONAssert.assertEquals("{name:\"Jason\"}", actual, false);		//Pass
	}

}
