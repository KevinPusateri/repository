package it.enaip.corso;

public class Variables {

	public static void main(String[] args) {
//		Gli interi possono essere scritti in vari formati:
//			Decimale: 19
//			Binario: 0b10011
//			Ottale: 023
//			Esadecimale: 0x13
//			Nel caso in cui il numero ecceda
//			la dimensione del tipo il compilatore restituirà errore.

		int a;
		int b = 19; 	// Decimale
		int c = 0b10011;// Binario
		int d = 023; 	// Ottale
		int e = 0x13; 	// Esadecimale
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
		System.out.println("sesso: " + sex);
		int[] fibonacci = { 1, 1, 2, 3, 5, 8, 13, 21 }; // etc....
		System.out.println(fibonacci[0]);

		int i, k, j = 3;
	}

}
