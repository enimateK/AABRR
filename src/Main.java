public class Main{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AABRR GrandArbre = new AABRR(50 ,75, 60);
		String val = "";
		System.out.println("coucou");
		GrandArbre.CreerSAD(78, 80, 80);
		GrandArbre.CreerSAG(9, 22, 9);
		GrandArbre.getAA().CreerSAG(62);
		GrandArbre.getAA().CreerSAD(55);
		GrandArbre.Parcours(val);
		System.out.println(val);
		System.out.println("Bye");
	}

}
