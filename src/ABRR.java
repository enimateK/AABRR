public class ABRR extends Arbre{
	private int racine;
	private ABRR SAG;
	private ABRR SAD;

	public int getRacine() {
		return racine;
	}

	/**
	 * @param args
	 */
	public ABRR(int racine){
		this.racine = racine;
		this.SAG = null;
		this.SAD = null;		
	}
	
	public void CreerSAG(int racine){
		this.SAG = new ABRR(racine);
	}
	
	public void CreerSAD(int racine){
		this.SAD = new ABRR(racine);
	}
	
	public void choixSA(int racineSA){
		if (racineSA <= this.racine){
			CreerSAD(racineSA);
		}else{
			CreerSAG(racineSA);
		}
	}

	public void setRacine(int racine){
	    this.racine = racine;
    }
	
	public String Parcours(String valeur){
			valeur = valeur + this.racine + ':';
			if (this.SAG != null) 
			{
				valeur = this.SAG.Parcours(valeur);
			}
			if (this.SAD != null) 
			{
				valeur = this.SAD.Parcours(valeur);
			}
		//}
		//super.Parcours(valeur);
		return valeur;
	}

	public void RandomCreationG(int nbNoeuds, int max, int min) {
        System.out.println(this.racine);
        if (nbNoeuds > 0) {
            int nombreAleatoire = min + (int) (Math.random() * ((max - min) + 1));
            this.CreerSAG(nombreAleatoire);
            this.SAG.RandomCreationG(nbNoeuds - 1, max, nombreAleatoire);
            int nombreAleatoire2 = min + (int) (Math.random() * ((max - min) + 1));
            this.CreerSAD(nombreAleatoire2);
            this.SAD.RandomCreationD(nbNoeuds - 1, nombreAleatoire2, nombreAleatoire);
        }
    }

	public void RandomCreationD(int nbNoeuds, int max, int min){
        System.out.println(this.racine);
        if (nbNoeuds > 0) {
            int nombreAleatoire = min + (int)(Math.random() * ((max - min) + 1));
            this.CreerSAG(nombreAleatoire);
            this.SAG.RandomCreationG(nbNoeuds - 1, max, nombreAleatoire);
            int nombreAleatoire2 = min + (int)(Math.random() * ((max - min) + 1));
            this.CreerSAD(nombreAleatoire2);
            this.SAD.RandomCreationD(nbNoeuds - 1, nombreAleatoire2, 1);
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
