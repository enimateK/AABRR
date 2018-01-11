import java.util.Scanner;

public class Main{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean sortie = false;
		AABRR arbre = new AABRR(null, null, null, null);
		while (sortie == false) {			
			System.out.println("Bienvenue !");
			System.out.println("0. Affichage de l'arbre");
			System.out.println("1. Lecture AABRR depuis fichier");
			System.out.println("2. Créer fichier depuis AABRR");
			System.out.println("3. Création d'un AABRR aléatoire");
			System.out.println("4. Verifier un AABRR");
			System.out.println("5. Recherche d'un entier dans un AABRR");
			System.out.println("6. Suppression d'un entier dans un AABRR");
			System.out.println("7. Insertion d'un entier dans un AABRR");
			System.out.println("8. ABR vers AABRR");
			System.out.println("9. AABRR vers ABR");
			System.out.println("x quitter");
			System.out.println("Veuillez faire un choix");
			Scanner scan = new Scanner(System.in);
			String choice = scan.nextLine();
			switch(choice) {
				case "0":
					String val = "";
					System.out.println("0. Affichage de l'arbre : ");
					val = arbre.Parcours(val);
					System.out.println(val);
					break;
				case "1":
					System.out.println("1. Lecture AABRR depuis fichier");
					System.out.println("Chemin du fichier contenant l'AABRR :");
					choice = scan.nextLine();
					arbre = new AABRR(choice);
					System.out.println("AABRR terminé");
					break;
					
				case "2":
					System.out.println("2. Créer fichier depuis AABRR");
					System.out.println("Indiquer le chemin où placer votre fichier");
					choice = scan.nextLine();
					System.out.println("Indiquer le nom de votre fichier");
					String nomFichier = scan.nextLine();
					arbre.Save(nomFichier, choice);
					break;
					
				case "3":
					int nbnoeud = 0;
					int max = 0;
					System.out.println("3. Création d'un AABRR aléatoire");
					System.out.println("Nombre de noeuds ?");
					choice = scan.nextLine();
					nbnoeud = Integer.parseInt(choice);
					System.out.println("Valeur maximale ?");
					choice = scan.nextLine();
					max = Integer.parseInt(choice);
					arbre = arbre.GenererAABRRAleatoire(nbnoeud, max);
					break;
				case "4":
					boolean verif = true;
					System.out.println("4. Verifier un AABRR");
					verif = arbre.verif(arbre);
					System.out.println(verif);
					break;
					
				case "5":
					System.out.println("5. Recherche d'un entier dans un AABRR");
					break;
				case "6":
					break;
				case "7":
					break;
				case "8":
					break;
				case "9":
					break;
				case "x": 
					sortie = true;
				default:
					break;
			}
		}
	}

}
