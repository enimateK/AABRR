public class Main{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AABRR GrandArbre = new AABRR(50 ,75, 60);
		String val = "";
		GrandArbre.CreerSAD(78, 80, 80);
		GrandArbre.CreerSAG(9, 22, 9);
		GrandArbre.getAA().CreerSAG(62);
		GrandArbre.getAA().CreerSAD(55);
		String arbre = GrandArbre.Parcours(val);

		AABRR Arbre = new AABRR(1, 1000, 500);
		Arbre.RandomCreationD(2, 500, 1);
		Arbre.RandomCreationG(2, 1000, 500);
	}

}
