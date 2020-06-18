package it.enaip.corso;

public class Dog {

	private String name;
	private String breed;
	private int age;
	public static final int NUMBER_OF_LEGS = 4;
	
	public Dog() {
		// costruttore vuoto
	}

	public Dog(String name, String breed, int age) {
		this.name = name;
		this.breed = breed;
		this.age = age;

	}


	public void run() {
		// do something
	}

//	public static void main(String[] args) {
//		Dog dog1 = new Dog(4);
//		dog1.name = "Fido";
//		dog1.breed = "Labrador";
//		// dog1.age = 4;
//
//		Dog dog2 = new Dog("Fido", "Bull terrier");
//		dog2.name = "Jack";
//		dog2.breed = "Bulldog";
//		dog2.age = 4;
//
//		dog1.run();
//		dog2.run();
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	@Override
	public String toString() {
		return "Dog [name=" + name + ", breed=" + breed + ", age=" + age + "]";
	}
	

}
