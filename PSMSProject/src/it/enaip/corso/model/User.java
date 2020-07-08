package it.enaip.corso.model;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

	public enum Type {
		CHILD("C"), OWNER("O"), SPOUSE("S");

		protected String descType;

		Type(String descType) {
			this.descType = descType;
		}

		public String getDescType() {
			return descType;
		}

		public void setDescType(String descType) {
			this.descType = descType;
		}

	}

	protected int id;
	protected String name;
	protected String surname;
	protected String birthDate;
	protected int age;
	protected Type type;
	Timestamp sqlTimestamp;

	public User() {
	}

	public User(int id) {
		this.id = id;
	}

	public User(int id, String name, String surname, String birthDate, int age, Type type) {
		this(name, surname, birthDate, age, type);
		this.id = id;
	}

	public User(String name, String surname, String birthDate, int age, Type type) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.age = age;
		this.type = type;
		this.sqlTimestamp = new Timestamp(System.currentTimeMillis());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Timestamp getSqlTimestamp() {
		return sqlTimestamp;
	}

	public void setSqlTimestamp(Timestamp sqlTimestamp) {
		this.sqlTimestamp = sqlTimestamp;
	}

	public String getValueType() {
		String ret = "";
		if (type == null)
			return null;

		switch (type) {
		case CHILD:
			ret = "C";
			break;
		case OWNER:
			ret = "O";
			break;
		case SPOUSE:
			ret = "S";
			break;
		default:
			break;
		}
		return ret;
	}

	public static Type getEnum(String t) {
		if (t.length() != 1)
			return null;

		Type type = null;
		switch (t) {
		case "C":
			type = Type.CHILD;
			break;
		case "O":
			type = Type.OWNER;
			break;
		case "S":
			type = Type.SPOUSE;
			break;
		default:
			break;
		}
		return type;

	}
	
	public JSONObject getJsonObject() throws JSONException {
		return JsonConverterUser.userToJson(this);
	}

}
