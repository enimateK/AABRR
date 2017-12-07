
public class ABRR extends Arbre{
	private int racine;
	private ABRR SAG;
	private ABRR SAD; 
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
	
	public String Parcours(String valeur){
		if (this.SAG == null && this.SAD == null){
			valeur = valeur + this.racine;	
		}else{
			valeur = valeur + this.racine + ':';
		}
		super.Parcours(valeur);
		return valeur;
	}
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
