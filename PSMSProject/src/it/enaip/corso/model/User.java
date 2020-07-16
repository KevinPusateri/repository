package it.enaip.corso.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	private int id;
	private String name;
	private String surname;
	private Date birthDate;
	private int age;
	private Type type;
	private Timestamp sqlTimestamp;
  
	public User() {
	}

	public User(int id) {
		this.id = id;
	}

	public User(int id, String name, String surname, Date birthDate, int age, Type type) {
		this(name, surname, birthDate, age, type);
		this.id = id;
	}

	public User(String name, String surname, Date birthDate, int age, Type type) {
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
//		if(name == null || name.equals("")) {
//			throw new NullPointerException();
//		}
		
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		
//		if(surname == null || surname.equals("")) {
//			throw new NullPointerException();
//		}
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
//		if(age < 0) {
//			throw new NumberFormatException();
//		}
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
	
	public String getDateformat() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(birthDate);
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
