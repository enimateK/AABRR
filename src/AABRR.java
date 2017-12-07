
public class AABRR extends Arbre {
	private int min; //minimum
	private int Max; // Maximum
	private ABRR AA;
	/**
	 * @param args
	 */
	public AABRR(int m, int M, int racine){
		super();
		this.min = m;
		this.Max = M;
		this.AA = new ABRR(racine);

	}
	
	public void CreerSAG(int m, int M, int racine){
		this.SAG = new AABRR(m,M,racine);
	}
	
	public void CreerSAD(int m, int M, int racine){
		this.SAD = new AABRR(m,M,racine);
	}
	
	public ABRR getAA(){
		return this.AA;
	}
	
	public String Parcours(String valeur){
		valeur = valeur + this.min + ':' +  this.Max + ';' ;
		AA.Parcours(valeur);
		super.Parcours(valeur);
		return valeur;
	}

	public void RandomCreationG(int nbNoeuds, int max, int min) {
		System.out.println(this.AA.getRacine());
		if (nbNoeuds > 0) {
			int nombreAleatoire = min + (int) (Math.random() * ((max - min) + 1));
			this.CreerSAG(min, max, nombreAleatoire);
			this.SAG.RandomCreationG(nbNoeuds - 1, max, nombreAleatoire);
			int nombreAleatoire2 = min + (int) (Math.random() * ((max - min) + 1));
			this.CreerSAD(min, max, nombreAleatoire2);
			this.SAD.RandomCreationD(nbNoeuds - 1, nombreAleatoire2, nombreAleatoire);
		}
	}

	public void RandomCreationD(int nbNoeuds, int max, int min){
		System.out.println(this.AA.getRacine());
		if (nbNoeuds > 0) {
			int nombreAleatoire = min + (int)(Math.random() * ((max - min) + 1));
			this.CreerSAG(min, max, nombreAleatoire);
			this.SAG.RandomCreationG(nbNoeuds - 1, max, nombreAleatoire);
			int nombreAleatoire2 = min + (int)(Math.random() * ((max - min) + 1));
			this.CreerSAD(min, max, nombreAleatoire2);
			this.SAD.RandomCreationD(nbNoeuds - 1, nombreAleatoire2, 1);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
