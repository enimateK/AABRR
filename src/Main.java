import src.AABRR_RANDOM;
import src.AABRR;
import java.util.Scanner;

public class Main{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Bienvenue !");
		System.out.println("1. Lecture AABRR depuis fichier");
		System.out.println("2. Créer fichier depuis AABRR");
		System.out.println("3. Création d'un AABRR aléatoire");
		System.out.println("4. Verifier un AABRR");
		System.out.println("5. Recherche d'un entier dans un AABRR");
		System.out.println("6. Suppression d'un entier dans un AABRR");
		System.out.println("7. Insertion d'un entier dans un AABRR");
		System.out.println("8. ABR vers AABRR");
		System.out.println("9. AABRR vers ABR");
		Scanner scan = new Scanner(System.in);



		AABRR_RANDOM Arbre = new AABRR_RANDOM();
		Arbre.GenererAABRRAleatoire(5, 100);

//		Arbre.RandomCreationD(1, 100, 50);
//		AABRR Arbre = new AABRR(1, 1000, 500);
//		AABRR SousArbre = new AABRR(1001, 1500, 1250);
//		Arbre.InsererAABRR(Arbre, SousArbre);


	}

}
