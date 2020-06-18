package interfaccia;

public class TestInterfaccia {

	public static void main(String[] args) throws Exception{

		Mela mela1 = new Mela("Golden", 4);
		Mela mela2 = new Mela("Stark", 4);
		Mela mela3 = new Mela("Golden", 4);

//		int c1 = mela1.compareTo(mela2);
//		int c2 = mela1.compareTo(mela3);

//		System.out.println("Mela1 = Mela2: " + ((mela1.compareTo(mela2) == 0) ? "Uguali" : "non sono uguali"));

		int result = 0;
		System.out.println("Mela1 = Mela3: " + ((mela1.compareTo(mela3) == 0) ? "Uguali" : "non sono uguali"));
//		System.out.println("Mela1 > Mela2: " + ((mela1.compareTo(mela2) > 0) ? "mela1 e' maggiore" : "Mela2 e' maggiore"));
		System.out.println("Mela1 > Mela3: " + ((mela1.compareTo(mela3) > 0) ? "mela1 e' maggiore" : "Mela3 e' maggiore"));

		
		Pera pera1= new Pera("Golden", 4);
		System.out.println("Pera1 = Mela1: " +
		((pera1.compareTo(mela1) == 0) ? "Uguali" : "non sono uguali"));

	}
}
