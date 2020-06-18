/**
 * Commento javadoc
 */
package it.enaip.corso;

/**
 * @author trieuris10
 *
 */
public class Helloworld {

	public static void variables() {
		int a;
		int b = 3;
		String saluto = "Ciao";

		final Dog ciro;

		Dog tony = new Dog();
		tony.setAge(5);
		int legs = tony.NUMBER_OF_LEGS;
		System.out.println(tony);

		float example = 4.543653636353f;
		System.out.println(example);
		double exampleother = 4.543653636353f;
		System.out.println(exampleother);
		double exmpale2 = 4.543653636353d;
		System.out.println(exmpale2);

		char sex = 'M';
		System.out.println("sesso: "+sex);
		int[] fibonacci = { 1, 1, 2, 3, 5, 8, 13, 21 }; // etc....
		System.out.println(fibonacci[0]);

		int i, k, j = 3;

	}

	public static void saluta() {
		int numeroAlunni = 10;

		for (int i = 1; i <= numeroAlunni; i++)
			System.out.println(i+": Ciao\r");
	}

	/**
	 * Calcolo fattoriale
	 * 
	 * @param n into to calculate
	 * @return fattoriale di n
	 */
	public static float calcoloFattoriale(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result = result * i;
		}

		return result;
	}

	/**
	 * @param args Your First Name
	 * 
	 */
	public static void main(String[] args) {

//		String x = "Hi " + args[0] + " " + args[1] + ", this is your first Java Program!";
//		System.out.println(x);
//		 Chiamata allo stantard output

//		variables();
		saluta();
//		saluta();
		
		
//		float fattorialeCalcolato = 0;
//		for (int i = 1; i <= 50; i++) {
//			fattorialeCalcolato = calcoloFattoriale(i);
//			System.out.println("I=" + i + " " + fattorialeCalcolato);
//		}
//		System.out.println("Fattoriale: " + fattorialeCalcolato);

	}

}
