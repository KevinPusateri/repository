package it.enaip.corso.model;

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
	
	public User() {}
	
	public User(int id) {
		this.id = id;
	}
	
	public User(int id,String name, String surname, String birthDate, int age, Type type) {
		this(name,surname,birthDate,age,type);
		this.id = id;
	}
	
	public User(String name, String surname, String birthDate, int age, Type type) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.age = age;
		this.type = type;
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
	
}
