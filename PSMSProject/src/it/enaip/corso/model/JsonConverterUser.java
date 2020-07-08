package it.enaip.corso.model;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonConverterUser {

	public static JSONObject userToJson(User user) throws JSONException {
		JSONObject jobj = new JSONObject();
		String str = "";
		int in = 0;

		in = user.getId();
		jobj.put("id", new Integer(in));

		str = user.getName();
		if (str != null && str.length() > 0) {
			jobj.put("name", str);
		}

		str = user.getSurname();
		if (str != null && str.length() > 0) {
			jobj.put("surname", str);
		}

		str = user.getBirthDate();
		if (str != null && str.length() > 0) {
			jobj.put("birthDate", str);
		}

		in = user.getAge();
		jobj.put("age", new Integer(in));

		str = user.getType().getDescType();
		if (str != null && str.length() > 0) {
			jobj.put("type", str);
		}

		Timestamp time = user.getSqlTimestamp();
		if (time != null) {
			jobj.put("sqlTimestamp", time);
		}
		return jobj;
	}

	public static User jsonToUser(JSONObject json) throws JSONException {
		User user = new User();
		int in = 0;
		String str = "";

		if (json.has("id")) {
			in = Integer.parseInt(json.get("id").toString());
			user.setId(in);
		}

		if (json.has("name")) {
			str = json.get("name").toString();
			user.setName(str);
		}

		if (json.has("surname")) {
			str = json.get("surname").toString();
			user.setSurname(str);
		}
		
		if (json.has("birthDate")) {
			str = json.get("birthDate").toString();
			user.setBirthDate(str);
		}
		
		if (json.has("age")) {
			in = Integer.parseInt(json.get("age").toString());
			user.setAge(in);
		}
		
		if (json.has("type")) {
			str = json.get("type").toString();
			user.setType(User.getEnum(str));
		}
		
		if (json.has("time")) {
			Timestamp time = (Timestamp) json.get("sqlTimestamp");
			user.setSqlTimestamp(time);
		}

		return user;
	}

}
