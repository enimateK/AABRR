public abstract class Arbre {
	protected Arbre SAG;
	protected Arbre SAD;
	/**
	 * @param args
	 */
	public Arbre(){
		this.SAG = null;
		this.SAD = null;
	}
	
	public String Parcours(String valeur){
		if (this.SAG != null){
			this.SAG.Parcours(valeur);
		}
		if (this.SAD != null){
			this.SAG.Parcours(valeur);
		}		
		return valeur;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
